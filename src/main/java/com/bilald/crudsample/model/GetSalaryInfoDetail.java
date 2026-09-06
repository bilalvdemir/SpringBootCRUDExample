package com.bilald.crudsample.model;

import com.bilald.crudsample.common.enums.Currency;
import com.bilald.crudsample.common.enums.EmployeeRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GetSalaryInfoDetail {

    private double salary;
    private Currency currency;
    private Instant createdAt;
    private EmployeeRole employeeRole;
}
