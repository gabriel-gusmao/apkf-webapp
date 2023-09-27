package com.kungfu.apkfwebapp.services;

import com.kungfu.apkfwebapp.api.model.MemberDTO;
import com.kungfu.apkfwebapp.api.model.MemberListDTO;

public interface MemberService {

    MemberListDTO getAllMembers();

    MemberListDTO getMembersByFirstName(String firstName);

    MemberListDTO getMembersByLastName(String lastName);

    MemberDTO getMemberById(int id);

    MemberDTO createNewMember(MemberDTO memberDTO);

    MemberDTO saveMemberByDTO(int id, MemberDTO memberDTO);

    MemberDTO patchMember(int id, MemberDTO memberDTO);

    void deleteMemberById(int id);
}
