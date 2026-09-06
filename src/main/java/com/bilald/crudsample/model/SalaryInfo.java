package com.bilald.crudsample.model;

import com.bilald.crudsample.common.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SalaryInfo extends Employee {

    private Currency currency;
    private double salary;
}
