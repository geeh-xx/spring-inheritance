package com.interview.spring.codelitt.dataprovider.entities;

import com.interview.spring.codelitt.dataprovider.entities.inheritance.MemberEntity;
import com.interview.spring.codelitt.enums.MemberTypeEnum;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

import static com.interview.spring.codelitt.enums.MemberTypeEnum.ValuesMember.CONTRACTOR;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@DiscriminatorValue(value = CONTRACTOR)
@Entity
public class ContractorEntity extends MemberEntity {

    private Integer contractDuration;

}
