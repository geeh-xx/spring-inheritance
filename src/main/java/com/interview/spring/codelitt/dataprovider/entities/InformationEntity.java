package com.interview.spring.codelitt.dataprovider.entities;

import com.interview.spring.codelitt.dataprovider.entities.inheritance.MemberEntity;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.CascadeType.ALL;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_informations")
public class InformationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_information")
    private Long idInformations;

    private String country;

    private String currency;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "id_member", referencedColumnName = "id_member")
    private MemberEntity member;
}
