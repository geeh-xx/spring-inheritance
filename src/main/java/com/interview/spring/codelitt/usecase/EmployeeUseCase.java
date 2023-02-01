package com.interview.spring.codelitt.usecase;

import com.interview.spring.codelitt.dataprovider.entities.EmployeeEntity;
import com.interview.spring.codelitt.dataprovider.entities.InformationEntity;
import com.interview.spring.codelitt.dataprovider.repository.EmployeeRepository;
import com.interview.spring.codelitt.entrypoint.dto.MemberDTO;
import com.interview.spring.codelitt.enums.EmployeeRoleEnum;
import com.interview.spring.codelitt.enums.MemberTypeEnum;
import com.interview.spring.codelitt.infrastructure.exception.MemberValidationException;
import com.interview.spring.codelitt.usecase.mapper.MemberMapper;
import com.interview.spring.codelitt.usecase.strategy.MemberActions;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import static com.interview.spring.codelitt.enums.MemberTypeEnum.EMPLOYEE;

@Service
@RequiredArgsConstructor
public class EmployeeUseCase implements MemberActions {

    private final EmployeeRepository repository;
    private final InformationUseCase informationUseCase;
    private final MemberMapper mapper;
    private final MessageSource messageSource;


    @Override
    public MemberDTO create(MemberDTO payload) {
        checkParticularity(payload.getRole());
        EmployeeEntity employeeEntity = EmployeeEntity.builder().memberEntity(mapper.dtoToEntity(payload))
                                                       .role(payload.getRole()).build();

        InformationEntity informationEntity = informationUseCase.buildInformation(employeeEntity);
        employeeEntity.setInformation(informationEntity);

        EmployeeEntity entitySaved = repository.save(employeeEntity);
        MemberDTO memberDTO = mapper.entityToDto(entitySaved);
        memberDTO.setRole(entitySaved.getRole());

        return memberDTO;
    }

    @Override
    public MemberDTO findById(Long id) {
        EmployeeEntity employeeEntity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id not found"));
        MemberDTO memberDTO = mapper.entityToDto(employeeEntity);
        memberDTO.setRole(employeeEntity.getRole());
        return memberDTO;
    }

    @Override
    public MemberDTO update(MemberDTO payload) {
        return create(payload);
    }

    @Override
    public void deleteById(Long id) {
         repository.deleteById(id);
    }


    @Override
    public MemberTypeEnum getMemberType() {
        return EMPLOYEE;
    }

    @Override
    public <T> void checkParticularity(T particularity) {
        if(!(particularity instanceof EmployeeRoleEnum))
            throw new MemberValidationException("Error in Role");
    }

}
