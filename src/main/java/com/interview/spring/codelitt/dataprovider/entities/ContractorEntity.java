package com.interview.spring.codelitt.dataprovider.entities;

import com.interview.spring.codelitt.dataprovider.entities.inheritance.MemberEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

import static com.interview.spring.codelitt.enums.MemberTypeEnum.ValuesMember.CONTRACTOR;




@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue(value = CONTRACTOR)
public class ContractorEntity extends MemberEntity {

    private Integer contractDuration;

    @Builder
    public ContractorEntity(MemberEntity memberEntity , Integer contractDuration){
        super(memberEntity);
        this.contractDuration = contractDuration;
    }

}
