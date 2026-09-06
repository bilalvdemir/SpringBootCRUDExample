package com.bilald.crudsample.service;

import com.bilald.crudsample.dto.request.UpdateRoleBaseSalaryRequest;
import com.bilald.crudsample.common.enums.EmployeeRole;
import com.bilald.crudsample.model.RoleBaseSalary;

import java.util.List;

public interface RoleBaseSalaryService {

    RoleBaseSalary getByRole(EmployeeRole role);

    RoleBaseSalary getById(String id);

    List<RoleBaseSalary> getAll();

    double getBaseSalaryByRole(EmployeeRole role);

    RoleBaseSalary create(RoleBaseSalary roleBaseSalary);

    RoleBaseSalary update(String id, UpdateRoleBaseSalaryRequest request);

    void delete(EmployeeRole role);
}
