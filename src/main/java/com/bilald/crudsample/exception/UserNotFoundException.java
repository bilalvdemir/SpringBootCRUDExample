package com.bilald.crudsample.exception;

public class UserNotFoundException extends BusinessException {

    public UserNotFoundException(String username) {
        super(ErrorCode.USER_NOT_FOUND, "User not found: " + username);
    }
}
