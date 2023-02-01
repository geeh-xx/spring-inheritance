package com.interview.spring.codelitt.usecase;

import com.interview.spring.codelitt.dataprovider.MemberRepository;
import com.interview.spring.codelitt.dataprovider.entities.ContractorEntity;
import com.interview.spring.codelitt.dataprovider.entities.InformationEntity;
import com.interview.spring.codelitt.entrypoint.dto.MemberDTO;
import com.interview.spring.codelitt.enums.MemberTypeEnum;
import com.interview.spring.codelitt.infrastructure.exception.MemberValidationException;
import com.interview.spring.codelitt.usecase.inheritance.MemberActions;
import com.interview.spring.codelitt.usecase.mapper.MemberMapper;
import com.interview.spring.codelitt.webprovider.InformationWebProvider;
import com.interview.spring.codelitt.webprovider.client.CountryCurrencyClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import static com.interview.spring.codelitt.enums.MemberTypeEnum.CONTRACTOR;

@Component
public class ContractorUseCase extends InformationWebProvider implements MemberActions {

    private final MemberRepository repository;
    private final MemberMapper mapper;


    public ContractorUseCase(CountryCurrencyClient client, MessageSource messageSource, MemberRepository repository, MemberMapper mapper) {
        super(client, messageSource);
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public MemberDTO createMember(MemberDTO memberDTO) {
        checkContractDuration(memberDTO.getContractDuration());
        InformationEntity informationEntity = buildInformationByCountry(memberDTO.getCountry());
        ContractorEntity contractorEntity = mapper.dtoToEntity(memberDTO, informationEntity);
        ContractorEntity save = repository.save(contractorEntity);

        return mapper.entityToDto(save, getMemberType());
    }

    @Override
    public MemberTypeEnum getMemberType() {
        return CONTRACTOR;
    }

    private void checkContractDuration(Integer contractDuration){
        if(contractDuration == null || contractDuration == 0)
            throw new MemberValidationException("Error in a contract duration");
    }
}
