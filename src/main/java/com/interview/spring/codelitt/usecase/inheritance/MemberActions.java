package com.interview.spring.codelitt.usecase.inheritance;

import com.interview.spring.codelitt.dataprovider.entities.InformationEntity;
import com.interview.spring.codelitt.entrypoint.dto.MemberDTO;
import com.interview.spring.codelitt.enums.MemberTypeEnum;

public interface MemberActions {

    MemberDTO createMember(MemberDTO memberDTO);
    MemberTypeEnum getMemberType();
}
