package com.interview.spring.codelitt.dataprovider.entities.inheritance;

import com.interview.spring.codelitt.dataprovider.entities.InformationEntity;
import com.interview.spring.codelitt.enums.MemberTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.List;

import static jakarta.persistence.DiscriminatorType.STRING;
import static jakarta.persistence.InheritanceType.SINGLE_TABLE;


@Entity(name = "tb_members")
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = STRING)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_member")
    private Long idMember;

    private BigInteger salary;

    private String name;

    @OneToOne(mappedBy = "member",cascade = CascadeType.ALL)
    private InformationEntity information;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "tb_member_tag", joinColumns = @JoinColumn(name = "id_member"))
    @Column(name = "tags")
    private List<String> tags;

}
