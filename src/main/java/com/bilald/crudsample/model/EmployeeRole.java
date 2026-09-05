package com.bilald.crudsample.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmployeeRole {

    DEVELOPER("Developer"),
    SENIOR_DEVELOPER("Senior Developer"),
    TEAM_LEAD("Team Lead"),
    MANAGER("Manager"),
    SENIOR_MANAGER("Senior Manager"),
    DIRECTOR("Director"),
    VP("Vice President"),
    CTO("Chief Technology Officer");

    private final String displayName;
}
