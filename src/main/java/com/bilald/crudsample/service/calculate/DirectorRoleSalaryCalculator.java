package com.bilald.crudsample.service.calculate;

import com.bilald.crudsample.common.enums.Currency;
import com.bilald.crudsample.common.enums.EmployeeRole;
import com.bilald.crudsample.model.GetSalaryInfoDetail;
import com.bilald.crudsample.service.RoleBaseSalaryService;
import com.bilald.crudsample.service.currency.CurrencyConverterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class DirectorRoleSalaryCalculator implements RoleSalaryCalculator {

    private static final double SENIORITY_MULTIPLIER = 1.08;

    private final CurrencyConverterService currencyConverterService;
    private final RoleBaseSalaryService roleBaseSalaryService;

    @Override
    public EmployeeRole getEmployeeRole() {
        return EmployeeRole.DIRECTOR;
    }

    @Override
    public double calculateSalary(GetSalaryInfoDetail salaryInfoDetail) {
        double convertedSalary = currencyConverterService.convert(
                getBaseSalaryByRole(salaryInfoDetail.getEmployeeRole()),
                Currency.USD,
                salaryInfoDetail.getCurrency()
        );

        long months = ChronoUnit.MONTHS.between(salaryInfoDetail.getCreatedAt(), LocalDateTime.now());
        double seniority = months / 12.0;
        double multiplier = Math.pow(SENIORITY_MULTIPLIER, seniority);

        return convertedSalary * multiplier;
    }

    private double getBaseSalaryByRole(EmployeeRole employeeRole) {
        return roleBaseSalaryService.getBaseSalaryByRole(employeeRole);
    }
}
