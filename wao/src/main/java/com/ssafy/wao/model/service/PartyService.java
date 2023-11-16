package com.ssafy.wao.model.service;

import com.ssafy.wao.model.dto.EventDto;
import com.ssafy.wao.model.dto.PartyDto;
import com.ssafy.wao.model.dto.UserDto;

import java.util.List;

public interface PartyService {

    /*
        파티를 만드는 기능입니다.
        PartyDto 정보 받아서 SQL INSERT
    */
    public int makeParty(PartyDto partyDto);

    /*
        파티를 조회하는 기능입니다.
        파티 리스트에서 파티를 클릭했을 때 사용합니다.
        partyNo 받아서 SQL SELECT
    */
    public PartyDto selectParty(int partyNo);

    /*
        모든 파티를 조회하는 기능입니다.
     */
    public List<PartyDto> selectEntireParty();

    /*
        일치하는 파티넘버를 찾아 모든 파티 구성원을 조회하는 기능힙니다.
        partyNo 받아서 List<UserDto> 반환
     */
    public List<UserDto> selectMembers(int partyNo);

    /*
        일치하는 파티넘버를 찾아 모든 파티 구성원을 조회하는 기능힙니다.
        partyNo를 받아서 UserDto 반환
     */
    public UserDto selectLeader(int partyNo);

    /*
        파티넘버와 일치하는 모든 이벤트를 조회하는 기능입니다.
        partyNo를 받아서 EventDto 반환
     */
    public List<EventDto> selectAllEvents(int partyNo);

    /*
        파티넘버와 일치하는 파티의 정원수를 조회하는 기능입니다.
        partyNo를 받아서 size 값 반환
    */
    public int getPartySize(int partyNo);

    public int insertPartyMember(PartyDto partyDto);
}
