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

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/party")
@Api(tags = "파티 컨트롤러")
@CrossOrigin("*")
public class PartyRestController {

    @Autowired
    PartyService partyService;

    @PostMapping("new")
    ResponseEntity<Void> makeParty(PartyDto partyDto) {

        System.out.println(partyDto);
        int generatedPartyId = 0;
        if (partyDto != null) {
            partyService.makeParty(partyDto);
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

                int res = partyService.insertPartyMember(insertMember);
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

    @DeleteMapping("applicants")
    ResponseEntity<?> declineApplication(@RequestBody PartyDto partyDto) {
        int res = partyService.declineApply(partyDto);

        if (res > 0) return new ResponseEntity<Void>(HttpStatus.OK);

        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }
}