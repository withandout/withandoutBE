package com.ssafy.wao.model.dao;

import com.ssafy.wao.model.dto.EventDto;

import java.util.List;

public interface EventDao {
    public List<EventDto> isAffordable(EventDto eventDto);

    public int createEvent(EventDto eventDto);

    public int applyEvent(EventDto eventDto);

    public int cancelEvent(EventDto eventDto);

    public int selectAllEvents(EventDto eventDto);
}
