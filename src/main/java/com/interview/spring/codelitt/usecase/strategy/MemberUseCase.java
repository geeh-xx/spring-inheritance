package com.interview.spring.codelitt.usecase.strategy;

import com.interview.spring.codelitt.dataprovider.MemberRepository;
import com.interview.spring.codelitt.dataprovider.entities.inheritance.MemberEntity;
import com.interview.spring.codelitt.entrypoint.dto.MemberDTO;
import com.interview.spring.codelitt.gateway.MemberGateway;
import com.interview.spring.codelitt.usecase.mapper.MemberMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class MemberUseCase implements MemberGateway {
    private final MemberFactory memberFactory;
    private final MemberRepository repository;

    private final MemberMapper mapper;
    @Override
    public MemberDTO createMember(MemberDTO payload)  {
        MemberActions member = memberFactory.getMember(payload.getType());
        return member.create(payload);
    }

    @Override
    public MemberDTO findMemberById(Long id) {
        MemberEntity memberEntity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id not found"));
        return mapper.entityToDto(memberEntity);
    }

    @Override
    public List<MemberDTO> findAll(Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public MemberDTO updateMember(MemberDTO memberDTO) {
        return null;
    }

    @Override
    public MemberDTO deleteMemberById(Long id) {
        return null;
    }

    @Override
    public MemberDTO findMemberByTag(List<String> tags) {
        return null;
    }

}
