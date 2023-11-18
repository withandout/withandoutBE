package com.ssafy.wao.model.service;

import com.ssafy.wao.model.dao.EventDao;
import com.ssafy.wao.model.dto.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    @Autowired
    EventDao eventDao;

    @Override
    public List<EventDto> isAffordable(EventDto eventDto) {
        return eventDao.isAffordable(eventDto);
    };

    @Override
    public int createEvent(EventDto eventDto) {
        // 먼저 생성 가능한지 먼저 확인함.
        // 자신의 일정 조회해서 겹치는 일정이 있는지.

        return 0;
    }

    @Override
    public int applyEvent(EventDto eventDto) {
        // 먼저 신청 가능한지 먼저 확인함.
        // 자신의 일정 조회해서 겹치는 일정이 있는지.
        return 0;
    }

    @Override
    public int cancelEvent(EventDto eventDto) {
        return 0;
    }

    @Override
    public int selectAllEvents(EventDto eventDto) {
        return 0;
    }
}
