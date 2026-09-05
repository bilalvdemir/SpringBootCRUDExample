package com.bilald.crudsample.service;

import com.bilald.crudsample.dto.request.UpdateEmployeeRequest;
import com.bilald.crudsample.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

    Employee getEmployeeByUsername(String username);

    Employee getEmployeeById(String id);

    Page<Employee> getAllEmployees(Pageable pageable);

    Employee createEmployee(Employee user);

    Employee updateEmployee(String id, UpdateEmployeeRequest request);

    void deleteEmployee(String username);
}
