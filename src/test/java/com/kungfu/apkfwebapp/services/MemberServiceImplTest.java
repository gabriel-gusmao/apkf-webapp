package com.kungfu.apkfwebapp.services;

import com.kungfu.apkfwebapp.api.mapper.MemberMapper;
import com.kungfu.apkfwebapp.api.model.MemberDTO;
import com.kungfu.apkfwebapp.api.model.MemberListDTO;
import com.kungfu.apkfwebapp.domain.Member;
import com.kungfu.apkfwebapp.repositories.MemberRepository;
import com.kungfu.apkfwebapp.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class MemberServiceImplTest {

    public static final String FIRSTNAME_1 = "Jake";
    public static final String LASTNAME_1 = "Finn";
    public static final int ID_1 = 1;
    public static final String FIRSTNAME_2 = "Daniel";
    public static final String LASTNAME_2 = "San";
    public static final int ID_2 = 2;
    public static final String CITY_1 = "Campinas";

    @Mock
    MemberRepository memberRepository;

    UserRepository userRepository;

    MemberMapper memberMapper = MemberMapper.INSTANCE;

    MemberService memberService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        memberService = new MemberServiceImpl(memberMapper, memberRepository, userRepository);
    }

    @Test
    void getAllMembers() {
        //given
        Member member1 = getMember1();
        Member member2 = getMember2();

        when(memberRepository.findAll()).thenReturn(Arrays.asList(member1, member2));

        //when
        MemberListDTO memberListDTO = memberService.getAllMembers();

        //then
        assertEquals(2, memberListDTO.getMembers().size());
    }

//    @Test
//    void getMembersByFirstName() {
//    }
//
//    @Test
//    void getMembersByLastName() {
//    }

    @Test
    void getMemberById() {
        //given
        Member member1 = getMember1();

        when(memberRepository.findById(anyInt())).thenReturn(Optional.ofNullable(member1));

        //when
        MemberDTO memberDTO = memberService.getMemberById(1);

        //then
        assertEquals(FIRSTNAME_1, memberDTO.getFirstName());
    }

    @Test
    void createNewMember() {
        //given
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setFirstName(FIRSTNAME_1);
        memberDTO.setLastName(LASTNAME_1);

        Member savedMember = new Member();
        savedMember.setFirstName(memberDTO.getFirstName());
        savedMember.setLastName(memberDTO.getLastName());
        savedMember.setId(1);

        when(memberRepository.save(any(Member.class))).thenReturn(savedMember);

        //when
        MemberDTO savedDTO = memberService.createNewMember(memberDTO);

        //then
        assertEquals(memberDTO.getFirstName(), savedDTO.getFirstName());
        assertEquals(memberDTO.getLastName(), savedDTO.getLastName());
        assertEquals("/member/1", savedDTO.getMemberUrl());
    }

    @Test
    void saveMemberByDTO() {
        //given
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setFirstName(FIRSTNAME_1);
        memberDTO.setLastName(LASTNAME_1);

        Member savedMember = new Member();
        savedMember.setFirstName(memberDTO.getFirstName());
        savedMember.setLastName(memberDTO.getLastName());
        savedMember.setId(1);

        when(memberRepository.save(any(Member.class))).thenReturn(savedMember);

        //when
        MemberDTO savedDTO = memberService.saveMemberByDTO(1, memberDTO);

        //then
        assertEquals(memberDTO.getFirstName(), savedDTO.getFirstName());
        assertEquals(memberDTO.getLastName(), savedDTO.getLastName());
        assertEquals("/member/1", savedDTO.getMemberUrl());
    }

//    @Test
//    void patchMember() {
//    }

    @Test
    void deleteMemberById() {

        int id = 1;

        memberRepository.deleteById(id);

        verify(memberRepository, times(1)).deleteById(anyInt());
    }

    private Member getMember1() {
        Member member = new Member();
        member.setFirstName(FIRSTNAME_1);
        member.setLastName(LASTNAME_1);
        member.setId(ID_1);
        return member;
    }

    private Member getMember2() {
        Member member = new Member();
        member.setFirstName(FIRSTNAME_2);
        member.setLastName(LASTNAME_2);
        member.setId(ID_2);
        return member;
    }
}