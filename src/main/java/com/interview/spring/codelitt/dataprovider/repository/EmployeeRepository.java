package com.interview.spring.codelitt.dataprovider.repository;

import com.interview.spring.codelitt.dataprovider.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
