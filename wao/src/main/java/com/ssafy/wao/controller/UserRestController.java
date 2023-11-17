package com.ssafy.wao.controller;


import com.ssafy.wao.model.dao.UserDao;
import com.ssafy.wao.model.dto.PartyDto;
import com.ssafy.wao.model.dto.UserDto;
import com.ssafy.wao.model.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@Api(tags = "유저 컨트롤러")
@CrossOrigin("*")
public class UserRestController {

    private final String WORKPATH = System.getProperty("user.home") + "/Desktop/withandoutBE";

    @Autowired
    UserService userService;

    @PostMapping("signup")
    ResponseEntity<Void> signup(UserDto userDto, @RequestPart(required = false) MultipartFile file) {
        // multipartformdata 확인 (RequestBody 풀고) == Null 일 경우 default Path 주기.

        int res = 0;

        if (file == null) {
            res = userService.signup(userDto);
        }
        else {
            String projectPath = WORKPATH + "/data/image/profile/user/";

            UUID uuid = UUID.randomUUID();

            // 파일 이미지 지정.
            String imgName = uuid + "_" + file.getOriginalFilename();

            // 경로 지정.
            File saveFile = new File(projectPath, imgName);

            try {
                file.transferTo(saveFile);
                userDto.setImgName(imgName);
                userDto.setImgPath(projectPath + imgName);
                res = userService.signup(userDto);
            } catch (Exception e) {
                return new ResponseEntity<Void> (HttpStatus.NOT_ACCEPTABLE);
            }
        }

        if (res > 0)
            return new ResponseEntity<Void> (HttpStatus.OK);
        return new ResponseEntity<Void> (HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("login")
    ResponseEntity<Void> login(@RequestBody UserDto userDto, HttpSession session) {
        UserDto loginUser = userService.login(userDto);

        if (loginUser == null) {
            return new ResponseEntity<Void> (HttpStatus.NOT_FOUND);
        }

        session.setAttribute("loginUser", loginUser);
        return new ResponseEntity<Void> (HttpStatus.OK);
    }

    @GetMapping("logout")
    ResponseEntity<Void> logout(HttpSession session) {
        session.invalidate();
        return new ResponseEntity<Void> (HttpStatus.OK);
    }

    @GetMapping("info/{nickname}")
    ResponseEntity<?> selectUser(@PathVariable String nickname) {
        UserDto user = userService.selectUser(nickname);

        if (user == null) return new ResponseEntity<Void> (HttpStatus.NOT_FOUND);

        user.setImgPath(user.getImgPath());

        return new ResponseEntity<UserDto> (user, HttpStatus.OK);
    }

    @PutMapping("info")
    ResponseEntity<Void> modifyUser(@RequestBody UserDto userDto) {
        int res = userService.modifyUser(userDto);

        if (res > 0) return new ResponseEntity<Void> (HttpStatus.OK);

        return new ResponseEntity<Void> (HttpStatus.NOT_FOUND);
    }

    @PutMapping("info/content")
    ResponseEntity<Void> modifyUserContent(@RequestBody UserDto userDto) {
        int res = userService.modifyUserContent(userDto);

        if (res > 0) return new ResponseEntity<Void> (HttpStatus.OK);

        return new ResponseEntity<Void> (HttpStatus.NOT_FOUND);
    }

    // 파일 업로드 시 RequestBody 사용 불가.
    @PutMapping("info/img")
    ResponseEntity<Void> modifyUserImg(UserDto userDto, @RequestPart MultipartFile file) {
        String projectPath = WORKPATH + "/data/image/profile/user/";

        UUID uuid = UUID.randomUUID();

        // 파일 이미지 지정.
        String imgName = uuid + "_" + file.getOriginalFilename();

        // 경로 지정.
        File saveFile = new File(projectPath, imgName);

        try {
            file.transferTo(saveFile);
            userDto.setImgName(imgName);
            userDto.setImgPath(projectPath + imgName);
            userService.modifyUserImg(userDto);
        } catch (Exception e) {
            return new ResponseEntity<Void> (HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<Void> (HttpStatus.OK);
    }

    // 지역 변경하면 인증 정보 바뀜.
    @PutMapping("info/region")
    ResponseEntity<Void> modifyUserRegion(@RequestBody UserDto userDto) {
        int res = userService.modifyUserRegion(userDto);

        if (res > 0) {
            userService.unauthorize(userDto);
            return new ResponseEntity<Void> (HttpStatus.OK);
        }

        return new ResponseEntity<Void> (HttpStatus.NOT_FOUND);
    }

    @PutMapping("auth")
    ResponseEntity<Void> authorize(@RequestBody UserDto userDto) {
        int res = userService.authorize(userDto);

        if (res > 0) return new ResponseEntity<Void> (HttpStatus.OK);

        return new ResponseEntity<Void> (HttpStatus.NOT_FOUND);
    }

    // party Controller 이동 여부 확인
    @GetMapping("myparty")
    ResponseEntity<List<PartyDto>> selectUserParties(@RequestBody UserDto userDto) {
        List<PartyDto> myParty = userService.selectUserParties(userDto);

        return new ResponseEntity<List<PartyDto>> (myParty, HttpStatus.OK);
    }
}
