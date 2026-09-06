package com.bilald.crudsample.dto.response;

import com.bilald.crudsample.common.enums.Currency;
import com.bilald.crudsample.common.enums.EmployeeRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleBaseSalaryResponse {

    private String id;
    private EmployeeRole role;
    private double baseSalary;
    private Currency currency;
    private Instant createdAt;
    private Instant updatedAt;
}
