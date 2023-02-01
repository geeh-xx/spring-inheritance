package com.interview.spring.codelitt.usecase;

import com.interview.spring.codelitt.dataprovider.repository.ContractorRepository;
import com.interview.spring.codelitt.dataprovider.repository.MemberRepository;
import com.interview.spring.codelitt.dataprovider.entities.ContractorEntity;
import com.interview.spring.codelitt.dataprovider.entities.InformationEntity;
import com.interview.spring.codelitt.entrypoint.dto.MemberDTO;
import com.interview.spring.codelitt.enums.MemberTypeEnum;
import com.interview.spring.codelitt.infrastructure.exception.MemberValidationException;
import com.interview.spring.codelitt.usecase.strategy.MemberActions;
import com.interview.spring.codelitt.usecase.mapper.MemberMapper;
import com.interview.spring.codelitt.webprovider.InformationWebProvider;
import com.interview.spring.codelitt.webprovider.client.CountryCurrencyClient;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.interview.spring.codelitt.enums.MemberTypeEnum.CONTRACTOR;

@Component
public class ContractorUseCase extends InformationWebProvider implements MemberActions {

    private final ContractorRepository repository;
    private final MemberMapper mapper;

    public ContractorUseCase(CountryCurrencyClient client, MessageSource messageSource, ContractorRepository repository, MemberMapper mapper) {
        super(client, messageSource);
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public MemberDTO create(MemberDTO payload) {

        checkParticularity(payload.getContractDuration());
        InformationEntity informationEntity = buildInformationByCountry(payload.getCountry());
        ContractorEntity contractorEntity = ContractorEntity.builder().memberEntity(mapper.dtoToEntity(payload))
                                                .contractDuration(payload.getContractDuration()).build();

        informationEntity.setMember(contractorEntity);
        contractorEntity.setInformation(informationEntity);

        ContractorEntity save = repository.save(contractorEntity);
        MemberDTO memberDTO = mapper.entityToDto(save);

        memberDTO.setContractDuration(save.getContractDuration());

        return memberDTO;
    }

    @Override
    public MemberDTO findById(Long id) {
        ContractorEntity contractorEntity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id not found"));
        MemberDTO memberDTO = mapper.entityToDto(contractorEntity);
        memberDTO.setContractDuration(contractorEntity.getContractDuration());
        return memberDTO;
    }

    @Override
    public MemberDTO update(MemberDTO memberDTO) {
        return null;
    }

    @Override
    public MemberDTO deleteById(Long id) {
        return null;
    }

    @Override
    public MemberTypeEnum getMemberType() {
        return CONTRACTOR;
    }

    @Override
    public <T> void checkParticularity(T particularity) {
        if(particularity == null || !(particularity instanceof Integer) || ((Integer) particularity) < 1)
            throw new MemberValidationException("Error in a contract duration");
    }


}
