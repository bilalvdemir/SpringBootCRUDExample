package com.bilald.crudsample.service.calculate;

import com.bilald.crudsample.common.enums.Currency;
import com.bilald.crudsample.common.enums.EmployeeRole;
import com.bilald.crudsample.model.GetSalaryInfoDetail;
import com.bilald.crudsample.service.RoleBaseSalaryService;
import com.bilald.crudsample.service.currency.CurrencyConverterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class StaffRoleSalaryCalculator implements RoleSalaryCalculator {

    private static final double SENIORITY_MULTIPLIER = 1.03;

    private final CurrencyConverterService currencyConverterService;
    private final RoleBaseSalaryService roleBaseSalaryService;

    @Override
    public EmployeeRole getEmployeeRole() {
        return EmployeeRole.STAFF;
    }

    @Override
    public double calculateSalary(GetSalaryInfoDetail salaryInfoDetail) {
        double convertedSalary = currencyConverterService.convert(
                getBaseSalaryByRole(),
                Currency.USD,
                salaryInfoDetail.getCurrency());

        log.info("[calculateSalary] - calculate starting for staff.");
        LocalDateTime createdAt = LocalDateTime.ofInstant(salaryInfoDetail.getCreatedAt(), ZoneId.systemDefault());
        long months = ChronoUnit.MONTHS.between(createdAt, LocalDateTime.now());
        double seniority = months / 12.0;
        double multiplier = Math.pow(SENIORITY_MULTIPLIER, seniority);

        return convertedSalary * multiplier;
    }

    private double getBaseSalaryByRole() {
        return roleBaseSalaryService.getBaseSalaryByRole(getEmployeeRole());
    }
}
