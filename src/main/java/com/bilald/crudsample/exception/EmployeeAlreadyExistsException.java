package com.bilald.crudsample.exception;

public class EmployeeAlreadyExistsException extends BusinessException {

    public EmployeeAlreadyExistsException(String identifier) {
        super(ErrorCode.EMPLOYEE_ALREADY_EXISTS, "Employee already exists: " + identifier);
    }
}
