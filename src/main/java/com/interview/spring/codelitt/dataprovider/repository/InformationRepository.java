package com.interview.spring.codelitt.dataprovider.repository;

import com.interview.spring.codelitt.dataprovider.entities.InformationEntity;
import com.interview.spring.codelitt.dataprovider.entities.inheritance.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InformationRepository extends JpaRepository<InformationEntity, Long> {


    Optional<InformationEntity> findByMember(MemberEntity member);
}
