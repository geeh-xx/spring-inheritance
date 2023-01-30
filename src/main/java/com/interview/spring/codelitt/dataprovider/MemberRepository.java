package com.interview.spring.codelitt.dataprovider;

import com.interview.spring.codelitt.dataprovider.entities.inheritance.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
}
