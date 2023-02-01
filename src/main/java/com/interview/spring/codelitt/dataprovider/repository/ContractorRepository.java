package com.interview.spring.codelitt.dataprovider.repository;

import com.interview.spring.codelitt.dataprovider.entities.ContractorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractorRepository extends JpaRepository<ContractorEntity, Long> {
}
