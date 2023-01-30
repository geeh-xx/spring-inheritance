package com.interview.spring.codelitt.entrypoint.dto;

import com.interview.spring.codelitt.enums.EmployeeRoleEnum;
import com.interview.spring.codelitt.enums.MemberTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MemberDTO {

    private Long id;
    private String name;
    private Double salary;
    private EmployeeRoleEnum role;
    private MemberTypeEnum type;
    private List<String> tags;
    private String country;

}
