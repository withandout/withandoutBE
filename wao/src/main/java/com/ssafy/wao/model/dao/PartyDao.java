package com.ssafy.wao.model.dao;

import com.ssafy.wao.model.dto.EventDto;
import com.ssafy.wao.model.dto.PartyDto;
import com.ssafy.wao.model.dto.UserDto;

import java.util.List;

public interface PartyDao {

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
        현재 참여 가능한(여석이 존재하는) 파티 조회.
     */
    public List<PartyDto> selectAvailableParty();

    /*
        일치하는 파티넘버를 찾아 파티 내 모든 구성원을 조회하는 기능힙니다.
        partyNo 받아서 List<UserDto> 반환
     */
    public List<UserDto> selectMembers(int partyNo);

    /*
        일치하는 파티넘버를 찾아 파티 내 모든 신청자를 조회하는 기능힙니다.
        partyNo 받아서 List<UserDto> 반환
     */
    public List<UserDto> selectApplicants(int partyNo);

    /*
        일치하는 파티넘버를 찾아 모든 파티 구성원을 조회하는 기능힙니다.
        partyNo를 받아서 UserDto 반환
     */
    public UserDto selectLeader(int partyNo);

    /*
        파티넘버와 일치하는 모든 이벤트를 조회하는 기능입니다.
        params: partyNo, invitedDate(현재 시간 기준 일주일 조회)
        return:
     */
    public List<EventDto> selectAllEvents(PartyDto partyDto);

    /*
        파티넘버와 일치하는 파티의 정원수를 조회하는 기능입니다.
        partyNo를 받아서 size 값 반환
     */
    public int getPartySize(int partyNo);

    /*
        파티 멤버 신청 승인 기능입니다.
        update
        params : PartyDto partyDto
        result : int (승인 여부)
     */
    public int acceptApply(PartyDto partyDto);

    /*
        파티 멤버 신청 거절 기능입니다.
        delete
        params : PartyDto partyDto
        result : int (승인 여부)
     */
    public int declineApply(PartyDto partyDto);

    /*
        파티 신청 기능입니다.
        post
        params : PartyDto partyDto
        result : int (성공 여부)
     */
    public int insertPartyMember(PartyDto partyDto);

    /*
        파티 이미지 수정 기능입니다.
        put
        params : PartyDto partyDto
        result : int (성공 여부)
     */
    public int modifyPartyImg(PartyDto partyDto);

    /*
        파티의 정보 중복 여부 확인
        params : PartyDto partyDto
        result : PartyDto (중복 레코드)
     */
    public PartyDto validatePartyInfo(PartyDto partyDto);
}
