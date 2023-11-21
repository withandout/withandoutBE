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

    /*
        해당 시간에 참여중인 이벤트가 있는지?
        파티 구분 없이 유저 참여중인 이벤트 목록 반환.
        params: partyNo, userNo, sttTime, endTime

    @GetMapping("myevents")
    ResponseEntity<?> getSelectedTimeEvents(@PathVariable int userNo) {
        List<EventDto> list = eventService.isAffordable(eventDto);

        if (list == null) return new ResponseEntity<Void> (HttpStatus.NOT_ACCEPTABLE);

        return new ResponseEntity<List<EventDto>>(list, HttpStatus.OK);
    }
    */

    /*
        이벤트 생성 메서드입니다.
        params: partyNo, userNo, sttTime, endTime
        - 중복된 시간에 이미 다른 파티 참여하고 있으면 생성 불가 : 예외 처리
        - 이벤트 생성 시 참여자도 자동 참여 해줘야함.
        - 따라서 Auto Increment 된 값을 받아와야 합니다.
     */
    @PostMapping("new")
    ResponseEntity<Void> makeEvent(@RequestBody EventDto eventDto) {
        int res = eventService.createEvent(eventDto);

        if (res > 0)
            return new ResponseEntity<Void>(HttpStatus.OK);
        return new ResponseEntity<Void> (HttpStatus.NOT_ACCEPTABLE);
    }

    /*
        이벤트 참여 메서드입니다.
        params: eventNo, userNo, sttTime, endTime
        - 중복된 시간에 이미 다른 파티 참여하고 있으면 생성 불가 : 예외 처리
        - users_events 테이블에 기록.
     */
    @PostMapping("join")
    ResponseEntity<Void> joinEvent(@RequestBody EventDto eventDto) {
        int res = eventService.applyEvent(eventDto);

        if (res > 0)
            return new ResponseEntity<Void>(HttpStatus.OK);
        return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
    }

    /*
        이벤트 참여 취소 메서드입니다.
        params: eventNo, userNo
     */
    @DeleteMapping("cancel")
    ResponseEntity<Void> cancelEvent(@RequestBody EventDto eventDto) {
        int res = eventService.cancelEvent(eventDto);

        if (res > 0)
            return new ResponseEntity<Void>(HttpStatus.OK);
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
}
