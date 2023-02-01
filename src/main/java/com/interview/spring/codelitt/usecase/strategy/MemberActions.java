package com.interview.spring.codelitt.usecase.strategy;

import com.interview.spring.codelitt.entrypoint.dto.MemberDTO;
import com.interview.spring.codelitt.enums.MemberTypeEnum;

import java.util.List;

public interface MemberActions {

    MemberDTO create(MemberDTO memberDTO);

    MemberDTO findByid(Long id);

    List<MemberDTO> findAll();

    List<MemberDTO> findAll(Integer pageNumber, Integer pageSize);


    MemberDTO update(MemberDTO memberDTO);

    MemberDTO deleteById(Long id);

    MemberTypeEnum getMemberType();

    <T> void checkParticularity(T particularity);
}
