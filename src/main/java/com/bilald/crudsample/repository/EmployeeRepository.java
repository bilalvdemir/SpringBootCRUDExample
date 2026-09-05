package com.bilald.crudsample.repository;

import com.bilald.crudsample.model.Employee;
import com.bilald.crudsample.model.EmployeeRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

    Optional<Employee> findByUsername(String username);

    Optional<Employee> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    void deleteByUsername(String username);

    List<Employee> findByDepartment(String department);

    Page<Employee> findByDepartment(String department, Pageable pageable);

    List<Employee> findByRole(EmployeeRole role);

    Page<Employee> findByRole(EmployeeRole role, Pageable pageable);

    Page<Employee> findByDepartmentAndRole(String department, EmployeeRole role, Pageable pageable);

    long countByDepartment(String department);

    long countByRole(EmployeeRole role);
}
