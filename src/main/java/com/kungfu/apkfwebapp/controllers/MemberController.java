package com.kungfu.apkfwebapp.controllers;

import com.kungfu.apkfwebapp.api.model.MemberDTO;
import com.kungfu.apkfwebapp.api.model.MemberListDTO;
import com.kungfu.apkfwebapp.services.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(MemberController.BASE_URL)
public class MemberController {

    public static final String BASE_URL = "/member";

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public MemberListDTO getListOfMembers() {
        return new MemberListDTO(memberService.getAllMembers().getMembers());
    }

//    @GetMapping("/firstname/{firstName}")
//    @ResponseStatus(HttpStatus.OK)
//    public MemberListDTO getMembersByFirstName(@PathVariable String firstName) {
//        return new MemberListDTO(memberService.getMembersByFirstName(firstName).getMembers());
//    }
//
//    @GetMapping("/lastname/{lastName}")
//    @ResponseStatus(HttpStatus.OK)
//    public MemberListDTO getMembersByLastName(@PathVariable String lastName) {
//        return new MemberListDTO((memberService.getMembersByLastName(lastName).getMembers()));
//    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MemberDTO getMemberById(@PathVariable int id) {
        return memberService.getMemberById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberDTO createNewMember(@RequestBody MemberDTO memberDTO) {
        return memberService.createNewMember(memberDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MemberDTO updateMember(@PathVariable int id, @RequestBody MemberDTO memberDTO) {
        return memberService.saveMemberByDTO(id, memberDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MemberDTO patchMember(@PathVariable int id, @RequestBody MemberDTO memberDTO) {
        return memberService.patchMember(id, memberDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMember(@PathVariable int id) {
        memberService.deleteMemberById(id);
    }
}
