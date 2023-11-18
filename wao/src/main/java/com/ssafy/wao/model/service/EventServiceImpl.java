package com.ssafy.wao.model.service;

import com.ssafy.wao.model.dao.EventDao;
import com.ssafy.wao.model.dto.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

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
        // 유저가 해당 시간대에 참여중인 일정 반환.
        List<EventDto> eventsApplied = eventDao.isAffordable(eventDto);

        // 참여 중인 일정이 있다면 실패
        if (eventsApplied.size() != 0) return 0;
        if (eventDao.createEvent(eventDto) == 0) return 0;
        if (eventDao.joinEvent(eventDto) == 0) return 0;

        return 1;
    }

    @Override
    public int applyEvent(EventDto eventDto) {
        return eventDao.joinEvent(eventDto);
    }

    @Override
    public int cancelEvent(EventDto eventDto) {
        return eventDao.cancelEvent(eventDto);
    }
}
