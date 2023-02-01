package com.interview.spring.codelitt.usecase;

import com.interview.spring.codelitt.dataprovider.repository.ContractorRepository;
import com.interview.spring.codelitt.dataprovider.repository.InformationRepository;
import com.interview.spring.codelitt.dataprovider.entities.ContractorEntity;
import com.interview.spring.codelitt.dataprovider.entities.InformationEntity;
import com.interview.spring.codelitt.entrypoint.dto.MemberDTO;
import com.interview.spring.codelitt.enums.MemberTypeEnum;
import com.interview.spring.codelitt.infrastructure.exception.MemberValidationException;
import com.interview.spring.codelitt.usecase.strategy.MemberActions;
import com.interview.spring.codelitt.usecase.mapper.MemberMapper;
import com.interview.spring.codelitt.webprovider.CurrencyWebProvider;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.interview.spring.codelitt.enums.MemberTypeEnum.CONTRACTOR;

@Service
@RequiredArgsConstructor
public class ContractorUseCase  implements MemberActions {

    private final ContractorRepository repository;

    private final InformationUseCase informationUseCase;
    private final MemberMapper mapper;
    private final CurrencyWebProvider webProvider;
    private final MessageSource messageSource;



    @Override
    public MemberDTO create(MemberDTO payload) {

        checkParticularity(payload.getContractDuration());

        ContractorEntity contractorEntity = ContractorEntity.builder().memberEntity(mapper.dtoToEntity(payload))
                                                .contractDuration(payload.getContractDuration()).build();

        InformationEntity informationEntity = informationUseCase.saveInformation(contractorEntity);
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
    public MemberDTO update(MemberDTO payload) {
        return this.create(payload);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
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
