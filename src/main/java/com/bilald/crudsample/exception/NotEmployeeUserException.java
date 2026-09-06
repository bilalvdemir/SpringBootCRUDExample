package com.bilald.crudsample.exception;

public class NotEmployeeUserException extends BusinessException {

    public NotEmployeeUserException(String username) {
        super(ErrorCode.USER_NOT_FOUND, "User not employee: " + username);
    }
}
