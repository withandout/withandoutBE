package com.ssafy.wao.model.service;

import com.ssafy.wao.model.dto.PartyDto;
import com.ssafy.wao.model.dto.UserDto;

import java.util.List;

public interface UserService {
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
        int userNo 이용.
     */
    public UserDto selectUser(String nickname);

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
        유저 프로필 이미지 변경
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
    public List<PartyDto> selectUserParties(String nickname);

    /*
        유저의 정보 중복 여부 확인
        UserDto userDto
     */
    public int validateUserInfo(UserDto userDto);
}
