package com.ssafy.wao.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/party")
@Api(tags = "파티 컨트롤러")
@CrossOrigin("*")
public class PartyRestController {
}
