package com.bilald.crudsample.exception;

public class UserAlreadyExistsException extends BusinessException {

    public UserAlreadyExistsException(String username) {
        super(ErrorCode.USER_ALREADY_EXISTS, "User already exists: " + username);
    }
}
