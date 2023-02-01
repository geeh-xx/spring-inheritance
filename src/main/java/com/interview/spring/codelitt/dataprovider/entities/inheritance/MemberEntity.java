package com.interview.spring.codelitt.dataprovider.entities.inheritance;

import com.interview.spring.codelitt.dataprovider.entities.InformationEntity;
import com.interview.spring.codelitt.enums.EmployeeRoleEnum;
import com.interview.spring.codelitt.enums.MemberTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.DiscriminatorType.STRING;
import static jakarta.persistence.InheritanceType.JOINED;
import static jakarta.persistence.InheritanceType.SINGLE_TABLE;


@Entity(name = "tb_members")
@Inheritance(strategy = JOINED)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_member")
    private Long idMember;

    private Double salary;

    private String name;

    @Enumerated(EnumType.STRING)
    private MemberTypeEnum type;


    @OneToOne(mappedBy = "member",cascade = CascadeType.ALL)
    private InformationEntity information;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "tb_member_tag", joinColumns = @JoinColumn(name = "id_member"))
    @Column(name = "tags")
    private List<String> tags;
    public MemberEntity(MemberEntity memberEntity) {
        this.idMember = memberEntity.getIdMember();
        this.salary = memberEntity.getSalary();
        this.name = memberEntity.getName();
        this.information = memberEntity.getInformation();
        this.tags = memberEntity.getTags();
        this.type = memberEntity.getType();
    }

}
