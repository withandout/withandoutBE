package com.ssafy.wao.model.service;

import com.ssafy.wao.model.dao.EventDao;
import com.ssafy.wao.model.dao.PartyDao;
import com.ssafy.wao.model.dao.UserDao;
import com.ssafy.wao.model.dto.EventDto;
import com.ssafy.wao.model.dto.PartyDto;
import com.ssafy.wao.model.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PartyServiceImpl implements PartyService {

    @Autowired
    PartyDao partyDao;
    @Override
    public int makeParty(PartyDto partyDto) {
        return partyDao.makeParty(partyDto);
    }

    @Override
    public PartyDto selectParty(int partyNo) {
        return partyDao.selectParty(partyNo);
    }

    @Override
    public List<PartyDto> selectEntireParty() {
        return partyDao.selectEntireParty();
    }

    @Override
    public List<UserDto> selectMembers(int partyNo) {
        return partyDao.selectMembers(partyNo);
    }

    @Override
    public UserDto selectLeader(int partyNo) {
        return partyDao.selectLeader(partyNo);
    }

    @Override
    public List<EventDto> selectAllEvents(int partyNo) {
        return partyDao.selectAllEvents(partyNo);
    }

    @Override
    public int getPartySize(int partyNo) {
        return partyDao.getPartySize(partyNo);
    }

    @Override
    public int insertPartyMember(PartyDto partyDto) {
        return partyDao.insertPartyMember(partyDto);
    }

}
