package com.bilald.crudsample.exception;

import com.bilald.crudsample.common.enums.EmployeeRole;

public class RoleBaseSalaryAlreadyExistsException extends BusinessException {

    public RoleBaseSalaryAlreadyExistsException(EmployeeRole role) {
        super(ErrorCode.ROLE_BASE_SALARY_ALREADY_EXISTS, "Role base salary already exists for role: " + role.getDisplayName());
    }
}
