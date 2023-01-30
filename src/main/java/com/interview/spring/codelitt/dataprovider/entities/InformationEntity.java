package com.interview.spring.codelitt.dataprovider.entities;

import com.interview.spring.codelitt.dataprovider.entities.inheritance.MemberEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.CascadeType.ALL;

@Entity(name = "tb_informations")
@Getter
@Setter
public class InformationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_information")
    private Long idInformations;

    private String country;

    private String currency;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "id_member", referencedColumnName = "id_member")
    private MemberEntity member;
}
