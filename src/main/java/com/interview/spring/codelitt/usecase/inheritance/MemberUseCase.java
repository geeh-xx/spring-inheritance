package com.interview.spring.codelitt.usecase.inheritance;

import com.interview.spring.codelitt.dataprovider.entities.InformationEntity;
import com.interview.spring.codelitt.dataprovider.entities.inheritance.MemberEntity;
import com.interview.spring.codelitt.entrypoint.dto.MemberDTO;
import com.interview.spring.codelitt.gateway.MemberGateway;
import com.interview.spring.codelitt.webprovider.InformationWebProvider;
import com.interview.spring.codelitt.webprovider.client.CountryCurrencyClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class MemberUseCase implements MemberGateway {

    private final CountryCurrencyClient client;
    private MemberEntity memberEntity;
    private String fieldName;
    private String teste2;
    private final InformationWebProvider webProvider;
    private final MemberFactory memberFactory;
    @Override
    public MemberDTO createMember(MemberDTO memberDTO)  {

        InformationEntity informationEntity = webProvider.buildInformationByCountry(memberDTO.getCountry());

        MemberActions member = memberFactory.getMember(memberDTO.getType());
        member.createMember(memberDTO);


        return null;
    }



}
