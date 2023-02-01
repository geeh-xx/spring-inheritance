package com.interview.spring.codelitt.dataprovider.entities;

import com.interview.spring.codelitt.dataprovider.entities.inheritance.MemberEntity;
import com.interview.spring.codelitt.enums.MemberTypeEnum;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

import java.math.BigInteger;
import java.util.List;

import static com.interview.spring.codelitt.enums.MemberTypeEnum.ValuesMember.CONTRACTOR;




@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue(value = CONTRACTOR)
@Entity
public class ContractorEntity extends MemberEntity {

    private Integer contractDuration;

    @Builder
    public ContractorEntity(Integer contractDuration, Long idMember, BigInteger salary, String name,  InformationEntity information, List<String> tags) {
        super(idMember, salary, name, information, tags);
        this.contractDuration = contractDuration;
    }


}
