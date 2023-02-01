package com.interview.spring.codelitt.entrypoint.dto;

import com.interview.spring.codelitt.enums.EmployeeRoleEnum;
import com.interview.spring.codelitt.enums.MemberTypeEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MemberDTO {

    private Long idMember;

    @NotEmpty
    private String name;

    @NotNull
    @Min(value = 1)
    private Double salary;

    @NotNull
    private MemberTypeEnum type;

    private Long contractDuration;

    private EmployeeRoleEnum role;

    private List<String> tags;

    @NotEmpty
    private String country;

}
