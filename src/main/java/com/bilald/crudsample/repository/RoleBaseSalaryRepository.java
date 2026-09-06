package com.bilald.crudsample.repository;

import com.bilald.crudsample.common.enums.EmployeeRole;
import com.bilald.crudsample.model.RoleBaseSalary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleBaseSalaryRepository extends MongoRepository<RoleBaseSalary, String> {

    Optional<RoleBaseSalary> findByRole(EmployeeRole role);

    boolean existsByRole(EmployeeRole role);

    void deleteByRole(EmployeeRole role);
}
