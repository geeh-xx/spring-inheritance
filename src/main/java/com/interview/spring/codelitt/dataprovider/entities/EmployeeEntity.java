package com.interview.spring.codelitt.dataprovider.entities;

import com.interview.spring.codelitt.dataprovider.entities.inheritance.MemberEntity;
import com.interview.spring.codelitt.enums.EmployeeRoleEnum;
import jakarta.persistence.*;
import lombok.*;

import static com.interview.spring.codelitt.enums.MemberTypeEnum.ValuesMember.EMPLOYEE;

@Getter
@Setter
@NoArgsConstructor
@Entity(name ="tb_employee")
@PrimaryKeyJoinColumn(name = "id_employee")
public class EmployeeEntity extends MemberEntity {

    @Enumerated(EnumType.STRING)
    private EmployeeRoleEnum role;

    @Builder
    public EmployeeEntity(MemberEntity memberEntity, EmployeeRoleEnum role){
        super(memberEntity);
        this.role = role;
    }

}
