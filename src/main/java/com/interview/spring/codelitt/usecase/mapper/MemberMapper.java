package com.interview.spring.codelitt.usecase.mapper;

import com.interview.spring.codelitt.dataprovider.entities.ContractorEntity;
import com.interview.spring.codelitt.dataprovider.entities.InformationEntity;
import com.interview.spring.codelitt.entrypoint.dto.MemberDTO;
import com.interview.spring.codelitt.enums.MemberTypeEnum;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public ContractorEntity dtoToEntity(MemberDTO payload, InformationEntity informationEntity){
        ContractorEntity build = ContractorEntity.builder().contractDuration(payload.getContractDuration())
                .idMember(payload.getIdMember()).salary(payload.getSalary())
                .tags(payload.getTags()).name(payload.getName()).build();
        informationEntity.setMember(build);
        build.setInformation(informationEntity);
        return build;
    }

    public MemberDTO entityToDto(ContractorEntity entity, MemberTypeEnum type){
        return MemberDTO.builder().idMember(entity.getIdMember()).salary(entity.getSalary())
                .tags(entity.getTags()).type(type).contractDuration(entity.getContractDuration())
                .country(entity.getInformation().getCountry()).name(entity.getName()).build();
    }

}
