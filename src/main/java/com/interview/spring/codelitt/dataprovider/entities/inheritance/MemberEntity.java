package com.interview.spring.codelitt.dataprovider.entities.inheritance;

import com.interview.spring.codelitt.dataprovider.entities.CountryEntity;
import com.interview.spring.codelitt.enums.MemberTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.DiscriminatorType.STRING;
import static jakarta.persistence.InheritanceType.SINGLE_TABLE;

@Entity(name = "tb_member")
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = STRING)
@Getter
@Setter
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_member")
    private Long id;

    private Double salary;

    private String name;

    @OneToOne(mappedBy = "member")
    private CountryEntity country;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "tb_member_tag", joinColumns = @JoinColumn(name = "id_member"))
    @Column(name = "tags")
    private List<String> tags;

}
