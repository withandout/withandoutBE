package com.ssafy.wao.controller;


import com.ssafy.wao.model.dto.EventDto;
import com.ssafy.wao.model.dto.PartyDto;
import com.ssafy.wao.model.dto.UserDto;
import com.ssafy.wao.model.service.EventService;
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

    private final String WORKPATH = "src/assets/upload/";

    @Autowired
    PartyService partyService;

    @Autowired
    EventService eventService;

    @PostMapping("new")
    ResponseEntity<Void> makeParty(@RequestPart("party") PartyDto partyDto, @RequestPart(required = false, name="image") MultipartFile file) {

        // file uploading
        if (file == null) {

            String imgName = "defaultParty.png";

            partyDto.setImgName(imgName);
            partyDto.setImgPath(WORKPATH + imgName);
        }
        else {

            UUID uuid = UUID.randomUUID();

            // 파일 이미지 지정.
            String imgName = uuid + "_" + file.getOriginalFilename();

            // 경로 지정.
            File saveFile = new File(WORKPATH, imgName);

            try {
                file.transferTo(saveFile);
                partyDto.setImgName(imgName);
                partyDto.setImgPath(WORKPATH + imgName);
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

    @PostMapping("validate")
    ResponseEntity<Void> validatePartyInfo(@RequestBody PartyDto partyDto) {
        int res = partyService.validatePartyInfo(partyDto);

        if (res > 0) return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);

        return new ResponseEntity<Void> (HttpStatus.OK);
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

    @GetMapping("member/{partyNo}")
    ResponseEntity<?> selectMembers(@PathVariable int partyNo) {
        List<UserDto> partyMembers = partyService.selectMembers(partyNo);

        if (partyMembers == null) return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<List<UserDto>>(partyMembers, HttpStatus.OK);
    }

    @GetMapping("apply/{userNo}")
    ResponseEntity<?> selectApplicants(@PathVariable int userNo) {
        List<UserDto> partyApplicants = partyService.selectApplicants(userNo);

        if (partyApplicants == null) return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<List<UserDto>>(partyApplicants, HttpStatus.OK);
    }

    @GetMapping("member/leader/{partyNo}")
    ResponseEntity<?> selectLeader(@PathVariable int partyNo) {
        UserDto leader = partyService.selectLeader(partyNo);

        if (leader == null) return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<UserDto>(leader, HttpStatus.OK);
    }


    /*
        해당 파티의 모든 이벤트 반환
        params: partyNo, userNo, 현재 시간.
        - 현재 시각 이후를 시작 시간으로 갖는 이벤트를 반환
        - 현재 파티 넘버와 유저 넘버를 전달해서
        - 해당 파티 소속 이벤트를 조회하고, 해당 유저가 해당 파티에 참석 상태인지 아닌지도 반환 .
     */
    @PostMapping("event")
    ResponseEntity<?> selectAllEvents(@RequestBody PartyDto partyDto) {
        // 현재 시간 주입.
        partyDto.setInvitedDate(new Date(System.currentTimeMillis()));

        List<EventDto> partyEvents = partyService.selectAllEvents(partyDto);

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

        System.out.println("accept" + partyDto);

        int res = partyService.acceptApply(partyDto);

        if (res > 0) return new ResponseEntity<Void>(HttpStatus.OK);

        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("apply")
    ResponseEntity<?> declineApplication(@RequestBody PartyDto partyDto) {
        int res = partyService.declineApply(partyDto);

        System.out.println("decline" + partyDto);

        if (res > 0) return new ResponseEntity<Void>(HttpStatus.OK);

        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping("info/img")
    ResponseEntity<?> modifyPartyImg(PartyDto partyDto, @RequestPart MultipartFile file) {
        UUID uuid = UUID.randomUUID();

        // 파일 이미지 지정.
        String imgName = uuid + "_" + file.getOriginalFilename();

        // 경로 지정.
        File saveFile = new File(WORKPATH, imgName);

        try {
            file.transferTo(saveFile);
            partyDto.setImgName(imgName);
            partyDto.setImgPath(WORKPATH + imgName);
            partyService.modifyPartyImg(partyDto);
        } catch (Exception e) {
            return new ResponseEntity<Void> (HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}