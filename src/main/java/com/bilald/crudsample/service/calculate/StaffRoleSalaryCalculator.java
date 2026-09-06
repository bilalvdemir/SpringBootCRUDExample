package com.bilald.crudsample.service.calculate;

import com.bilald.crudsample.common.enums.Currency;
import com.bilald.crudsample.common.enums.EmployeeRole;
import com.bilald.crudsample.model.GetSalaryInfoDetail;
import com.bilald.crudsample.service.currency.CurrencyConverterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class StaffRoleSalaryCalculator implements RoleSalaryCalculator {

    private static final double SENIORITY_MULTIPLIER = 1.03;

    private final CurrencyConverterService currencyConverterService;

    @Override
    public EmployeeRole getEmployeeRole() {
        return EmployeeRole.STAFF;
    }

    @Override
    public double calculateSalary(GetSalaryInfoDetail salaryInfoDetail) {
        double convertedSalary = currencyConverterService.convert(salaryInfoDetail.getSalary(), Currency.USD, salaryInfoDetail.getCurrency());

        long months = ChronoUnit.MONTHS.between(salaryInfoDetail.getCreatedAt(), LocalDateTime.now());
        double seniority = months / 12.0;
        double multiplier = Math.pow(SENIORITY_MULTIPLIER, seniority);

        return convertedSalary * multiplier;
    }
}
