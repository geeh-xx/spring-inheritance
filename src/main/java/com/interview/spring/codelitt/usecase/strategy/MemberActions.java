package com.interview.spring.codelitt.usecase.strategy;

import com.interview.spring.codelitt.entrypoint.dto.MemberDTO;
import com.interview.spring.codelitt.enums.MemberTypeEnum;

public interface MemberActions {

    MemberDTO create(MemberDTO memberDTO);

    MemberDTO findById(Long id);

    MemberDTO update(MemberDTO memberDTO);

    void deleteById(Long id);

    MemberTypeEnum getMemberType();

    <T> void checkParticularity(T particularity);
}
