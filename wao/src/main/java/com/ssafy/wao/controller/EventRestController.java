package com.ssafy.wao.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schd")
@Api(tags = "일정 컨트롤러")
@CrossOrigin("*")
public class EventRestController {
}
