package com.interview.spring.codelitt.dataprovider.entities;

import com.interview.spring.codelitt.dataprovider.entities.inheritance.MemberEntity;
import com.interview.spring.codelitt.enums.MemberTypeEnum;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import static com.interview.spring.codelitt.enums.MemberTypeEnum.ValuesMember.CONTRACTOR;

@Entity
@DiscriminatorValue(value = CONTRACTOR)
@Getter
@Setter
public class ContractorEntity extends MemberEntity {

    private Integer contractDuration;

}
