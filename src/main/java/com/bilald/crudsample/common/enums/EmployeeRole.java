package com.bilald.crudsample.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmployeeRole {

    MANAGER("Manager"),
    DIRECTOR("Director"),
    STAFF("staff");

    private final String displayName;
}
