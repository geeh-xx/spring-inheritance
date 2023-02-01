package com.interview.spring.codelitt.gateway;

import com.interview.spring.codelitt.entrypoint.dto.MemberDTO;

import java.util.List;

public interface MemberGateway {

    MemberDTO createMember(MemberDTO memberDTO) ;

    MemberDTO findMemberById(Long id);

    MemberDTO updateMember(MemberDTO memberDTO);

    void deleteMemberById(Long id);

    MemberDTO findMemberByTag(List<String> tags);

}
