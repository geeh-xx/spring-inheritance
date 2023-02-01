package com.interview.spring.codelitt.dataprovider.entities;

import com.interview.spring.codelitt.dataprovider.entities.inheritance.MemberEntity;
import com.interview.spring.codelitt.enums.EmployeeRoleEnum;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import static com.interview.spring.codelitt.enums.MemberTypeEnum.ValuesMember.EMPLOYEE;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue(EMPLOYEE)
public class EmployeeEntity extends MemberEntity {

    @Enumerated(EnumType.STRING)
    private EmployeeRoleEnum role;

    @Builder
    public EmployeeEntity(MemberEntity memberEntity, EmployeeRoleEnum role){
        super(memberEntity);
        this.role = role;
    }

}
