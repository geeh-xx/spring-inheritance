package com.interview.spring.codelitt.usecase.inheritance;

import com.interview.spring.codelitt.entrypoint.dto.MemberDTO;
import com.interview.spring.codelitt.gateway.MemberGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class MemberUseCase implements MemberGateway {
    private final MemberFactory memberFactory;
    @Override
    public MemberDTO createMember(MemberDTO payload)  {
        MemberActions member = memberFactory.getMember(payload.getType());
        return member.createMember(payload);
    }



}
