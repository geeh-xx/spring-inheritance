package com.interview.spring.codelitt.dataprovider.entities;

import com.interview.spring.codelitt.dataprovider.entities.inheritance.MemberEntity;
import com.interview.spring.codelitt.enums.EmployeeRoleEnum;
import com.interview.spring.codelitt.enums.MemberTypeEnum;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import static com.interview.spring.codelitt.enums.MemberTypeEnum.ValuesMember.EMPLOYEE;

@Entity
@DiscriminatorValue(EMPLOYEE)
@Getter
@Setter
public class EmployeeEntity extends MemberEntity {

    @Enumerated(EnumType.STRING)
    private EmployeeRoleEnum role;

}