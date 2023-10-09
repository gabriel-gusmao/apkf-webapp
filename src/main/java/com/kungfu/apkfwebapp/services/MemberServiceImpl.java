package com.kungfu.apkfwebapp.services;

import com.kungfu.apkfwebapp.api.mapper.MemberMapper;
import com.kungfu.apkfwebapp.api.model.MemberDTO;
import com.kungfu.apkfwebapp.api.model.MemberListDTO;
import com.kungfu.apkfwebapp.controllers.MemberController;
import com.kungfu.apkfwebapp.domain.Member;
import com.kungfu.apkfwebapp.repositories.MemberRepository;
import com.kungfu.apkfwebapp.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final MemberRepository memberRepository;
    private final UserRepository userRepository;

    public MemberServiceImpl(MemberMapper memberMapper, MemberRepository memberRepository, UserRepository userRepository) {
        this.memberMapper = memberMapper;
        this.memberRepository = memberRepository;
        this.userRepository = userRepository;
    }

    @Override
    public MemberListDTO getAllMembers() {
        List<MemberDTO> memberDTOS = memberRepository
                .findAll()
                .stream()
                .map(member -> {
                    MemberDTO memberDTO = memberMapper.memberToMemberDTO(member);
                    memberDTO.setMemberUrl(getMemberUrl(member.getId()));
                    return memberDTO;
                })
                .collect(Collectors.toList());
        return new MemberListDTO(memberDTOS);
    }

//    @Override
//    public MemberListDTO getMembersByFirstName(String firstName) {
//        List<MemberDTO> memberDTOS = memberRepository
//                .findAll()
//                .stream()
//                .filter(s -> s.getFirstName().equals(firstName))
//                .map(member -> {
//                    MemberDTO memberDTO = memberMapper.memberToMemberDTO(member);
//                    memberDTO.setMemberUrl(getMemberUrl(member.getId()));
//                    return memberDTO;
//                })
//                .collect(Collectors.toList());
//        return new MemberListDTO(memberDTOS);
//    }
//
//    @Override
//    public MemberListDTO getMembersByLastName(String lastName) {
//        List<MemberDTO> memberDTOS = memberRepository
//                .findAll()
//                .stream()
//                .filter(s -> s.getLastName().equals(lastName))
//                .map(member -> {
//                    MemberDTO memberDTO = memberMapper.memberToMemberDTO(member);
//                    memberDTO.setMemberUrl(getMemberUrl(member.getId()));
//                    return memberDTO;
//                })
//                .collect(Collectors.toList());
//        return new MemberListDTO(memberDTOS);
//    }

    @Override
    public MemberDTO getMemberById(int id) {
        return memberRepository.findById(id)
                .map(memberMapper::memberToMemberDTO)
                .map(memberDTO -> {
                    memberDTO.setMemberUrl(getMemberUrl(id));
                    return memberDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public MemberDTO createNewMember(MemberDTO memberDTO) {
        return saveAndReturnDTO(memberMapper.memberDTOToMember(memberDTO));
    }

    @Override
    public MemberDTO saveMemberByDTO(int id, MemberDTO memberDTO) {

        Member memberToSave = memberMapper.memberDTOToMember(memberDTO);
        memberToSave.setId(id);

        return saveAndReturnDTO(memberToSave);
    }

    @Override
    public MemberDTO patchMember(int id, MemberDTO memberDTO) {
        return memberRepository.findById(id)
                .map(member -> {
                    if (memberDTO.getFirstName() != null) {
                        member.setFirstName(memberDTO.getFirstName());
                    }
                    if (memberDTO.getLastName() != null) {
                        member.setLastName(memberDTO.getLastName());
                    }
                    if (memberDTO.getBirthDate() != null) {
                        member.setBirthDate(memberDTO.getBirthDate());
                    }
                    if (memberDTO.getStartDate() != null) {
                        member.setStartDate(memberDTO.getStartDate());
                    }
                    if (memberDTO.getPhoneNumber() != null) {
                        member.setPhoneNumber(memberDTO.getPhoneNumber());
                    }
                    if (memberDTO.getAddress() != null) {
                        member.setAddress(memberDTO.getAddress());
                    }
                    if (memberDTO.getCity() != null) {
                        member.setCity(memberDTO.getCity());
                    }
                    if (memberDTO.getZipCode() != null) {
                        member.setZipCode(memberDTO.getZipCode());
                    }
                    if (memberDTO.getPhase() != null) {
                        member.setPhase(memberDTO.getPhase());
                    }
                    if (memberDTO.getTrainingGroup() != null) {
                        member.setTrainingGroup(memberDTO.getTrainingGroup());
                    }

                    return saveAndReturnDTO(member);
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteMemberById(int id) {
        memberRepository.deleteById(id);
    }

    private String getMemberUrl(int id) {
        return MemberController.BASE_URL + "/" + id;
    }

    private MemberDTO saveAndReturnDTO(Member member) {
        Member savedMember = memberRepository.save(member);

        MemberDTO returnDTO = memberMapper.memberToMemberDTO(savedMember);

        returnDTO.setMemberUrl(getMemberUrl(savedMember.getId()));

        return returnDTO;
    }
}
