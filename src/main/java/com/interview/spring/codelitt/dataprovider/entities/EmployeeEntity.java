package com.interview.spring.codelitt.dataprovider.entities;

import com.interview.spring.codelitt.dataprovider.entities.inheritance.MemberEntity;
import com.interview.spring.codelitt.enums.EmployeeRoleEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
