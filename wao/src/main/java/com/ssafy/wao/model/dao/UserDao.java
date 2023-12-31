package com.ssafy.wao.model.dao;

import com.ssafy.wao.model.dto.EventDto;
import com.ssafy.wao.model.dto.LogDto;
import com.ssafy.wao.model.dto.PartyDto;
import com.ssafy.wao.model.dto.UserDto;

import java.util.List;

public interface UserDao {
    /*
        회원가입 기능입니다.
        UserDto 정보 받아서 SQL INSERT
     */
    public int signup(UserDto userDto);

    /*
        로그인 기능입니다.
        id password로 SQL SELECT
        이후 Controller 단에서 Session에 등록.
     */
    public UserDto login(UserDto userDto);

    /*
        단일 유저 정보를 조회합니다.
        String nickname 이용.
        get 요청으로 들어오는 만큼, 유저의 식별자를 굳이 URL 상에서 표시하기는 좀...
     */
    public UserDto selectUser(int userNo);

    /*
        단일 유저 정보를 수정합니다.
        UserDto userDto 이용.
     */
    public int modifyUser(UserDto userDto);

    /*
        자기소개 수정 별도 진행
        String 이용.
     */
    public int modifyUserContent(UserDto userDto);

    /*
        지역 수정 별도 진행
        String 이용.
     */
    public int modifyUserRegion(UserDto userDto);

    /*
        유저 프로필 이미지 수정
     */
    public int modifyUserImg(UserDto userDto);
    /*
        인증 정보 초기화
     */
    public int unauthorize(UserDto userDto);

    /*
        동네 인증 정보 체크
        int userNo, String townName (API를 이용해 얻어온)
     */
    public int authorize(UserDto userDto);

    /*
        유저의 소속 파티 정보 확인
        int userNo
     */
    public List<PartyDto> selectUserParties(int userNo);

    /*
        유저의 정보 중복 여부 확인
        UserDto userDto
     */
    public UserDto validateUserInfo(UserDto userDto);

    public int isAuthorized(int userNo);


    /*
        유저의 일정 모두 반환
        params: int userNo
     */
    public List<EventDto> selectAllEvents(int userNo);

    /*
        현재 신청 가능한 파티 조회
        params : int userNo, String region
        result : List<PartyDto>
     */
    public List<PartyDto> selectAvailableParty(UserDto userDto);

    /*
        유저의 일주일간 러닝 기록 조회
        params : int userNo
        result : LogDto
     */
    public LogDto getUserRunningLog(int userNo);
}
