package com.interview.spring.codelitt.usecase;

import com.interview.spring.codelitt.entrypoint.dto.MemberDTO;
import com.interview.spring.codelitt.enums.MemberTypeEnum;
import com.interview.spring.codelitt.usecase.inheritance.MemberActions;
import org.springframework.stereotype.Component;

import static com.interview.spring.codelitt.enums.MemberTypeEnum.EMPLOYEE;

@Component
public class EmployeeUseCase implements MemberActions {

    @Override
    public MemberDTO createMember(MemberDTO memberDTO) {
        System.out.println("teste");
        return null;
    }

    @Override
    public MemberTypeEnum getMemberType() {
        return EMPLOYEE;
    }
}
