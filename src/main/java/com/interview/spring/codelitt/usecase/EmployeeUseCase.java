package com.interview.spring.codelitt.usecase;

import com.interview.spring.codelitt.dataprovider.MemberRepository;
import com.interview.spring.codelitt.dataprovider.entities.EmployeeEntity;
import com.interview.spring.codelitt.dataprovider.entities.InformationEntity;
import com.interview.spring.codelitt.entrypoint.dto.MemberDTO;
import com.interview.spring.codelitt.enums.EmployeeRoleEnum;
import com.interview.spring.codelitt.enums.MemberTypeEnum;
import com.interview.spring.codelitt.infrastructure.exception.MemberValidationException;
import com.interview.spring.codelitt.usecase.strategy.MemberActions;
import com.interview.spring.codelitt.usecase.mapper.MemberMapper;
import com.interview.spring.codelitt.webprovider.InformationWebProvider;
import com.interview.spring.codelitt.webprovider.client.CountryCurrencyClient;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.interview.spring.codelitt.enums.MemberTypeEnum.EMPLOYEE;

@Component
public class EmployeeUseCase extends InformationWebProvider implements MemberActions {

    private final MemberRepository repository;
    private final MemberMapper mapper;
    private  CountryCurrencyClient client;
    private  MessageSource messageSource;

    public EmployeeUseCase(MemberRepository repository, MemberMapper mapper, CountryCurrencyClient client, MessageSource messageSource) {
        super(client, messageSource);
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public MemberDTO create(MemberDTO payload) {
        checkParticularity(payload.getRole());

        InformationEntity informationEntity = buildInformationByCountry(payload.getCountry());
        EmployeeEntity employeeEntity = EmployeeEntity.builder().memberEntity(mapper.dtoToEntity(payload))
                                                       .role(payload.getRole()).build();

        informationEntity.setMember(employeeEntity);
        employeeEntity.setInformation(informationEntity);

        EmployeeEntity entitySaved = repository.save(employeeEntity);
        MemberDTO memberDTO = mapper.entityToDto(entitySaved);
        memberDTO.setType(getMemberType());
        memberDTO.setRole(entitySaved.getRole());

        return memberDTO;
    }

    @Override
    public MemberDTO findByid(Long id) {
        return null;
    }

    @Override
    public List<MemberDTO> findAll() {
        return null;
    }

    @Override
    public List<MemberDTO> findAll(Integer pageNumber, Integer pageSize) {
        PageRequest.of(pageNumber, pageSize);
        return null;
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
        return EMPLOYEE;
    }

    @Override
    public <T> void checkParticularity(T particularity) {
        if(particularity == null || !(particularity instanceof EmployeeRoleEnum))
            throw new MemberValidationException("Error in Role");
    }

}
