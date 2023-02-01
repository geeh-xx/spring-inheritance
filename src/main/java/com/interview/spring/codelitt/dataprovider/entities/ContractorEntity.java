package com.interview.spring.codelitt.dataprovider.entities;

import com.interview.spring.codelitt.dataprovider.entities.inheritance.MemberEntity;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.*;

import static com.interview.spring.codelitt.enums.MemberTypeEnum.ValuesMember.CONTRACTOR;




@Getter
@Setter
@NoArgsConstructor
@Entity(name ="tb_contractor")
@PrimaryKeyJoinColumn(name = "id_contractor")
public class ContractorEntity extends MemberEntity {


    @Column(name ="contract_duration")
    private Integer contractDuration;

    @Builder
    public ContractorEntity(MemberEntity memberEntity , Integer contractDuration){
        super(memberEntity);
        this.contractDuration = contractDuration;
    }

}
