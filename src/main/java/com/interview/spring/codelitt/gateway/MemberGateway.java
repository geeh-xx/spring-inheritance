package com.interview.spring.codelitt.gateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.interview.spring.codelitt.entrypoint.dto.MemberDTO;

public interface MemberGateway {

    MemberDTO createMember(MemberDTO memberDTO) ;

}
