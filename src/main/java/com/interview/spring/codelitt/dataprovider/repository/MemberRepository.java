package com.interview.spring.codelitt.dataprovider.repository;

import com.interview.spring.codelitt.dataprovider.entities.inheritance.MemberEntity;
import com.interview.spring.codelitt.dataprovider.projection.MemberProjectionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberProjectionType> findByIdMember(Long id);
}
