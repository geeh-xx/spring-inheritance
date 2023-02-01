package com.interview.spring.codelitt.usecase;

import com.interview.spring.codelitt.dataprovider.entities.InformationEntity;
import com.interview.spring.codelitt.dataprovider.entities.inheritance.MemberEntity;
import com.interview.spring.codelitt.dataprovider.repository.InformationRepository;
import com.interview.spring.codelitt.webprovider.CurrencyWebProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InformationUseCase {

    private final InformationRepository repository;
    private final CurrencyWebProvider webProvider;

    public InformationEntity saveInformation(MemberEntity memberEntity){
        String country = memberEntity.getInformation().getCountry();
        String currency = webProvider.getCurrency(memberEntity.getInformation().getCountry());

        Optional<InformationEntity> informationOpt = repository.findByMember(memberEntity);

        if(informationOpt.isEmpty())
            return InformationEntity.builder().country(country).currency(currency).member(memberEntity).build();

        InformationEntity informationEntity = informationOpt.get();
        informationEntity.setCountry(country);
        informationEntity.setCurrency(currency);

        return informationEntity;
    }
}
