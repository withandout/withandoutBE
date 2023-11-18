package com.ssafy.wao.model.service;

import com.ssafy.wao.model.dto.EventDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface EventService {

    public List<EventDto> isAffordable(EventDto eventDto);

    /*
        이벤트 생성
     */
    public int createEvent(EventDto eventDto);

    /*
        이벤트 참여. event_user 테이블 insert
     */
    public int applyEvent(EventDto eventDto);

    /*
        이벤트 참여 취소. event_user 테이블에서 delete
     */
    public int cancelEvent(EventDto eventDto);
}
