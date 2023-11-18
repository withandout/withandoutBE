package com.ssafy.wao.controller;


import com.ssafy.wao.model.dto.EventDto;
import com.ssafy.wao.model.service.EventService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
@Api(tags = "일정 컨트롤러")
@CrossOrigin("*")
public class EventRestController {

    @Autowired
    EventService eventService;

    @GetMapping("myevents/{userNo}")
    ResponseEntity<?> getEvents(EventDto eventDto) {
        List<EventDto> list = eventService.isAffordable(eventDto);

        if (list == null) return new ResponseEntity<Void> (HttpStatus.NOT_ACCEPTABLE);

        return new ResponseEntity<List<EventDto>>(list, HttpStatus.OK);
    }
}
