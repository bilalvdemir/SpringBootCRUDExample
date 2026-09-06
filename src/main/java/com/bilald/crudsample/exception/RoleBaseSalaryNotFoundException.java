package com.bilald.crudsample.exception;

import com.bilald.crudsample.common.enums.EmployeeRole;

public class RoleBaseSalaryNotFoundException extends BusinessException {

    public RoleBaseSalaryNotFoundException(EmployeeRole role) {
        super(ErrorCode.ROLE_BASE_SALARY_NOT_FOUND, "Role base salary not found for role: " + role.getDisplayName());
    }

    public RoleBaseSalaryNotFoundException(String id) {
        super(ErrorCode.ROLE_BASE_SALARY_NOT_FOUND, "Role base salary not found with id: " + id);
    }
}
