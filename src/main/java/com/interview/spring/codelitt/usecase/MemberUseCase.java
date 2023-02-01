package com.interview.spring.codelitt.usecase;

import com.interview.spring.codelitt.dataprovider.projection.MemberProjectionType;
import com.interview.spring.codelitt.dataprovider.repository.MemberRepository;
import com.interview.spring.codelitt.dataprovider.entities.inheritance.MemberEntity;
import com.interview.spring.codelitt.entrypoint.dto.MemberDTO;
import com.interview.spring.codelitt.gateway.MemberGateway;
import com.interview.spring.codelitt.usecase.mapper.MemberMapper;
import com.interview.spring.codelitt.usecase.strategy.MemberActions;
import com.interview.spring.codelitt.usecase.strategy.MemberFactory;
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

    @Override
    public MemberDTO createMember(MemberDTO payload)  {
        MemberActions member = memberFactory.getMember(payload.getType());
        return member.create(payload);
    }

    @Override
    public MemberDTO findMemberById(Long id) {
        MemberProjectionType projection = repository.findByIdMember(id).orElseThrow(() -> new EntityNotFoundException("Id not found"));
        MemberActions member = memberFactory.getMember(projection.getType());
        return member.findById(id);
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
