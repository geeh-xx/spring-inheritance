package com.interview.spring.codelitt.dataprovider.entities;

import com.interview.spring.codelitt.dataprovider.entities.inheritance.MemberEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.CascadeType.ALL;

@Entity(name = "tb_country")
@Getter
@Setter
public class CountryEntity {

    @Id
    private Long id;

    private String name;

    private String currency;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private MemberEntity member;
}
