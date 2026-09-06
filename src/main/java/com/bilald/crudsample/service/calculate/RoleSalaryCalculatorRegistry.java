package com.bilald.crudsample.service.calculate;

import com.bilald.crudsample.common.enums.EmployeeRole;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class RoleSalaryCalculatorRegistry {

    private final Map<EmployeeRole, RoleSalaryCalculator> roleSalaryCalculatorMap;

    public RoleSalaryCalculatorRegistry(List<RoleSalaryCalculator> roleSalaryCalculatorList) {
        this.roleSalaryCalculatorMap = roleSalaryCalculatorList.stream().collect(
                Collectors.toMap(RoleSalaryCalculator::getEmployeeRole,
                        Function.identity()));

    }

    public RoleSalaryCalculator getRoleSalaryCalculator(EmployeeRole role) {
        RoleSalaryCalculator strategy = roleSalaryCalculatorMap.get(role);
        return Optional.ofNullable(strategy)
                .orElseThrow(() -> new IllegalArgumentException("Role salary calculator not implemented."));
    }
}
