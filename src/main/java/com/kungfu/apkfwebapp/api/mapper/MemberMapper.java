package com.kungfu.apkfwebapp.api.mapper;

import com.kungfu.apkfwebapp.api.model.MemberDTO;
import com.kungfu.apkfwebapp.domain.Member;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {

    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    MemberDTO memberToMemberDTO(Member member);

    Member memberDTOToMember(MemberDTO memberDTO);
}
