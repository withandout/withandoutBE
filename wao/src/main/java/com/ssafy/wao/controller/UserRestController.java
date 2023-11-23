package com.ssafy.wao.controller;


import com.ssafy.wao.model.dao.UserDao;
import com.ssafy.wao.model.dto.EventDto;
import com.ssafy.wao.model.dto.LogDto;
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

    private final String WORKPATH = "/src/assets/upload/";

    @Autowired
    UserService userService;

    @PostMapping("signup")
    ResponseEntity<Void> signup(@RequestPart("user") UserDto userDto, @RequestPart(required = false, name="image") MultipartFile file) {
        // multipartformdata 확인 (RequestBody 풀고) == Null 일 경우 default Path 주기.

        int res = 0;

        if (file == null) {
            String imgName = "defaultUser.png";

            userDto.setImgName(imgName);
            userDto.setImgPath(WORKPATH + imgName);
            res = userService.signup(userDto);
        }
        else {
            UUID uuid = UUID.randomUUID();

            // 파일 이미지 지정.
            String imgName = uuid + "_" + file.getOriginalFilename();

            // 경로 지정.
            File saveFile = new File(WORKPATH, imgName);

            try {
                file.transferTo(saveFile);
                userDto.setImgName(imgName);
                userDto.setImgPath(WORKPATH + imgName);
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
    ResponseEntity<?> login(@RequestBody UserDto userDto, HttpSession session) {
        UserDto loginUser = userService.login(userDto);

        if (loginUser == null) {
            return new ResponseEntity<Void> (HttpStatus.NOT_FOUND);
        }

        session.setAttribute("loginUser", loginUser);
        return new ResponseEntity<UserDto> (loginUser, HttpStatus.OK);
    }

    @GetMapping("logout")
    ResponseEntity<Void> logout(HttpSession session) {
        session.invalidate();
        return new ResponseEntity<Void> (HttpStatus.OK);
    }

    @GetMapping("info/{userNo}")
    ResponseEntity<?> selectUser(@PathVariable int userNo) {
        UserDto user = userService.selectUser(userNo);

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
    @PostMapping("info/img")
    ResponseEntity<Void> modifyUserImg(@RequestPart("user") UserDto userDto, @RequestPart(required = false, name="image") MultipartFile file) {
        UUID uuid = UUID.randomUUID();

        // 파일 이미지 지정.
        String imgName = uuid + "_" + file.getOriginalFilename();

        // 경로 지정.
        File saveFile = new File(WORKPATH, imgName);

        try {
            file.transferTo(saveFile);
            userDto.setImgName(imgName);
            userDto.setImgPath(WORKPATH + imgName);
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
    @GetMapping("myparty/{userNo}")
    ResponseEntity<List<PartyDto>> selectUserParties(@PathVariable int userNo) {

        List<PartyDto> myParty = userService.selectUserParties(userNo);

        return new ResponseEntity<List<PartyDto>> (myParty, HttpStatus.OK);
    }

    @PostMapping("validate")
    ResponseEntity<Void> validateUserInfo(@RequestBody UserDto userDto) {
        int res = userService.validateUserInfo(userDto);

        if (res > 0) return new ResponseEntity<Void> (HttpStatus.NOT_ACCEPTABLE);
        return new ResponseEntity<Void> (HttpStatus.OK);
    }

    @GetMapping("auth/{userNo}")
    ResponseEntity<Integer> isAuthorized(@PathVariable int userNo) {

        Integer res = userService.isAuthorized(userNo);

        return new ResponseEntity<Integer> (res, HttpStatus.OK);
    }

    @GetMapping("event/{userNo}")
    ResponseEntity<?> selectAllEvents(@PathVariable int userNo) {
        List<EventDto> list = userService.selectAllEvents(userNo);

        if (list == null) return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<List<EventDto>>(list, HttpStatus.OK);
    }

    /*
        현재 신청 가능한 파티 조회
        params : int userNo, String region
        result : List<PartyDto>
     */
    @PostMapping("info/available")
    ResponseEntity<?> selectAvailableParty(@RequestBody UserDto userDto) {
        System.out.println(userDto);
        List<PartyDto> list = userService.selectAvailableParty(userDto);

        if (list == null)
            return new ResponseEntity<Void> (HttpStatus.NOT_FOUND);

        return new ResponseEntity<List<PartyDto>> (list, HttpStatus.OK);
    };

    /*
        유저의 일주일간 운동기록 조회
        params : int userNo
        result : LogDto
     */
    @GetMapping("log/{userNo}")
    ResponseEntity<?> getUserRunningLog(@PathVariable int userNo) {
        LogDto userLog = userService.getUserRunningLog(userNo);

        if (userLog == null) {
            userLog = new LogDto();
            userLog.setUserNo(userNo);
        }
        return new ResponseEntity<LogDto> (userLog, HttpStatus.OK);
    };
}
