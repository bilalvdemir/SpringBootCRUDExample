package com.bilald.crudsample.dto.response;


import com.bilald.crudsample.common.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SalaryInfoResponse extends EmployeeResponse {

    private Currency currency;
    private double salary;
}
