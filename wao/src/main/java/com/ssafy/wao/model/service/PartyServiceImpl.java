package com.ssafy.wao.model.service;

import com.ssafy.wao.model.dao.EventDao;
import com.ssafy.wao.model.dao.PartyDao;
import com.ssafy.wao.model.dao.UserDao;
import com.ssafy.wao.model.dto.*;
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
    public List<PartyDto> selectAvailableParty() {
        return partyDao.selectAvailableParty();
    }

    @Override
    public List<UserDto> selectMembers(int partyNo) {
        return partyDao.selectMembers(partyNo);
    }

    public List<UserDto> selectApplicants(int partyNo) {return partyDao.selectApplicants(partyNo);}

    @Override
    public UserDto selectLeader(int partyNo) {
        return partyDao.selectLeader(partyNo);
    }

    @Override
    public List<EventDto> selectAllEvents(PartyDto partyDto) {
        return partyDao.selectAllEvents(partyDto);
    }

    @Override
    public int getPartySize(int partyNo) {
        return partyDao.getPartySize(partyNo);
    }

    @Override
    public int acceptApply(PartyDto partyDto) {
        return partyDao.acceptApply(partyDto);
    }

    @Override
    public int declineApply(PartyDto partyDto) {
        return partyDao.declineApply(partyDto);
    }

    @Override
    public int insertPartyMember(PartyDto partyDto) {
        return partyDao.insertPartyMember(partyDto);
    }

    @Override
    public int modifyPartyImg(PartyDto partyDto) {
        return partyDao.modifyPartyImg(partyDto);
    }

    @Override
    public int validatePartyInfo(PartyDto partyDto) {
        return (partyDao.validatePartyInfo(partyDto) == null) ? 0 : 1;
    }

    @Override
    public List<ArticleDto> selectPartyArticle(int partyNo) {
        return partyDao.selectPartyArticle(partyNo);
    }

    @Override
    public List<LogDto> getPartyRunningLogs(int partyNo) {
        return partyDao.getPartyRunningLogs(partyNo);
    }
}