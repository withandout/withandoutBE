package com.ssafy.wao.controller;


import com.ssafy.wao.model.dto.EventDto;
import com.ssafy.wao.model.dto.PartyDto;
import com.ssafy.wao.model.dto.UserDto;
import com.ssafy.wao.model.service.PartyService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/party")
@Api(tags = "파티 컨트롤러")
@CrossOrigin("*")
public class PartyRestController {

    private final String WORKPATH = System.getProperty("user.home") + "/Desktop/withandoutBE";

    @Autowired
    PartyService partyService;

    @PostMapping("new")
    ResponseEntity<Void> makeParty(PartyDto partyDto, @RequestPart(required = false) MultipartFile file) {

        // file uploading
        if (file == null) {
            /*
                SET DEFAULT IMAGE
             */
            partyDto.setImgName("");
            partyDto.setImgPath("");
        }
        else {
            String projectPath = WORKPATH + "/data/image/profile/party/";

            UUID uuid = UUID.randomUUID();

            // 파일 이미지 지정.
            String imgName = uuid + "_" + file.getOriginalFilename();

            // 경로 지정.
            File saveFile = new File(projectPath, imgName);

            try {
                file.transferTo(saveFile);
                partyDto.setImgName(imgName);
                partyDto.setImgPath(projectPath + imgName);
            } catch (Exception e) {
                return new ResponseEntity<Void> (HttpStatus.NOT_ACCEPTABLE);
            }
        }

        // make leader member of new Party
        int generatedPartyId = 0;
        if (partyDto != null) {
            int res = partyService.makeParty(partyDto);

            // createError
            if (res == 0)
                return new ResponseEntity<Void> (HttpStatus.NOT_ACCEPTABLE);

            generatedPartyId = partyDto.getPartyNo();

            if (generatedPartyId > 0) {
                // users-parties 테이블 가서 generatedId , userId 전달

                PartyDto insertMember = new PartyDto();

                insertMember.setPartyNo(generatedPartyId);
                insertMember.setUserNo(partyDto.getUserNo());
                insertMember.setIsAccepted(1);

                Date sqlDate = new Date(System.currentTimeMillis());
                insertMember.setInvitedDate(sqlDate);
                insertMember.setAcceptedDate(sqlDate);

                res = 0;

                res = partyService.insertPartyMember(insertMember);

                // leader member application error
                if (res == 0) {
                    return new ResponseEntity<Void> (HttpStatus.NOT_ACCEPTABLE);
                }
                return new ResponseEntity<Void>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("info/{partyNo}")
    ResponseEntity<?> selectParty(int partyNo) {
        PartyDto party = partyService.selectParty(partyNo);

        if (party == null) return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<PartyDto>(party, HttpStatus.OK);
    }

    @GetMapping("info/all")
    ResponseEntity<?> selectEntireParty() {
        List<PartyDto> partyList = partyService.selectEntireParty();

        if (partyList == null) return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<List<PartyDto>>(partyList, HttpStatus.OK);
    }

    @GetMapping("info/available")
    ResponseEntity<?> selectAvailableParty() {
        List<PartyDto> partyList = partyService.selectAvailableParty();

        if (partyList == null) return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<List<PartyDto>>(partyList, HttpStatus.OK);
    }

    @GetMapping("members/{partyNo}")
    ResponseEntity<?> selectMembers(@PathVariable int partyNo) {
        List<UserDto> partyMembers = partyService.selectMembers(partyNo);

        if (partyMembers == null) return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<List<UserDto>>(partyMembers, HttpStatus.OK);
    }

    @GetMapping("apply/{partyNo}")
    ResponseEntity<?> selectApplicants(@PathVariable int partyNo) {
        List<UserDto> partyApplicants = partyService.selectApplicants(partyNo);

        if (partyApplicants == null) return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<List<UserDto>>(partyApplicants, HttpStatus.OK);
    }

    @GetMapping("members/leader/{partyNo}")
    ResponseEntity<?> selectLeader(@PathVariable int partyNo) {
        UserDto leader = partyService.selectLeader(partyNo);

        if (leader == null) return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<UserDto>(leader, HttpStatus.OK);
    }

    @GetMapping("events/{partyNo}")
    ResponseEntity<?> selectAllEvents(@PathVariable int partyNo) {
        List<EventDto> partyEvents = partyService.selectAllEvents(partyNo);

        if (partyEvents == null) return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<List<EventDto>>(partyEvents, HttpStatus.OK);
    }

    @PostMapping("apply")
    ResponseEntity<Void> applyToParty(@RequestBody PartyDto partyDto) {
        partyDto.setIsAccepted(0);
        int res = partyService.insertPartyMember(partyDto);

        if (res > 0) return new ResponseEntity<Void>(HttpStatus.OK);

        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping("apply")
    ResponseEntity<Void> acceptApply(@RequestBody PartyDto partyDto) {
        partyDto.setIsAccepted(1);
        int res = partyService.acceptApply(partyDto);

        if (res > 0) return new ResponseEntity<Void>(HttpStatus.OK);

        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("apply")
    ResponseEntity<?> declineApplication(@RequestBody PartyDto partyDto) {
        int res = partyService.declineApply(partyDto);

        if (res > 0) return new ResponseEntity<Void>(HttpStatus.OK);

        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping("info/img")
    ResponseEntity<?> modifyPartyImg(PartyDto partyDto, @RequestPart MultipartFile file) {
        String projectPath = WORKPATH + "/data/image/profile/party/";

        UUID uuid = UUID.randomUUID();

        // 파일 이미지 지정.
        String imgName = uuid + "_" + file.getOriginalFilename();

        // 경로 지정.
        File saveFile = new File(projectPath, imgName);

        try {
            file.transferTo(saveFile);
            partyDto.setImgName(imgName);
            partyDto.setImgPath(projectPath + imgName);
            partyService.modifyPartyImg(partyDto);
        } catch (Exception e) {
            return new ResponseEntity<Void> (HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    /*
        이벤트 생성
     */
    @PostMapping("event")
    ResponseEntity<?> createEvent(@RequestBody EventDto eventDto) {

        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    /*
        해당 시간 이벤트 조회.
        해당 시간 이벤트 생성이나 참여 시 먼저 확인함.
     */
    private boolean isAffordable(EventDto eventDto) {
        // 조회 결과가 0 이상이다 : 겹치는 시간대에 이벤트가 있다.
        int res = 0;


        return true;
    }

    /*
        이벤트 참여. event_user 테이블 insert
     */
    @PostMapping("event/apply")
    ResponseEntity<?> applyEvent(@RequestBody EventDto eventDto) {
        // 참여자 userNo, 이벤트 eventNo 넣어준다.

        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    /*
        이벤트 참여 취소. event_user 테이블에서 delete
     */
    @DeleteMapping("event/cancel")
    ResponseEntity<?> cancelEvent(@RequestBody EventDto eventDto) {

        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    /*
        해당 파티의 모든 이벤트 반환
     */
    @GetMapping ("event/all")
    ResponseEntity<?> selectAllEvents(@RequestBody EventDto eventDto) {

        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }
}