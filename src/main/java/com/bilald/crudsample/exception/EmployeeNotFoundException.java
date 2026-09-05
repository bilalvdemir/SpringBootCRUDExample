package com.bilald.crudsample.exception;

public class EmployeeNotFoundException extends BusinessException {

    public EmployeeNotFoundException(String identifier) {
        super(ErrorCode.EMPLOYEE_NOT_FOUND, "Employee not found: " + identifier);
    }
}
