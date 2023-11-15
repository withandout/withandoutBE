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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags = "유저 컨트롤러")
@CrossOrigin("*")
public class UserRestController {

    @Autowired
    UserService userService;

    @PostMapping("signup")
    ResponseEntity<Void> signup(@RequestBody UserDto userDto) {
        int res = userService.signup(userDto);

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

    @GetMapping("info/{userId}")
    ResponseEntity<?> selectUser(@PathVariable String userId) {
        UserDto user = userService.selectUser(userId);

        if (user == null) return new ResponseEntity<Void> (HttpStatus.NOT_FOUND);

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
