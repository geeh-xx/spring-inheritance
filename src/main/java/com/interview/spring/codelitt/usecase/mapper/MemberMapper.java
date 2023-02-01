package com.interview.spring.codelitt.usecase.mapper;

import com.interview.spring.codelitt.dataprovider.entities.InformationEntity;
import com.interview.spring.codelitt.dataprovider.entities.inheritance.MemberEntity;
import com.interview.spring.codelitt.entrypoint.dto.MemberDTO;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public MemberEntity dtoToEntity(MemberDTO payload){
        return new MemberEntity(payload.getIdMember(), payload.getSalary(),
                                            payload.getName(), payload.getType(),
                                            InformationEntity.builder().country(payload.getCountry()).build(),
                                            payload.getTags());
    }


    public MemberDTO entityToDto(MemberEntity entity){
        return MemberDTO.builder().idMember(entity.getIdMember()).salary(entity.getSalary())
                .tags(entity.getTags()).country(entity.getInformation().getCountry()).type(entity.getType())
                .name(entity.getName()).currency(entity.getInformation().getCurrency()).build();
    }

}
