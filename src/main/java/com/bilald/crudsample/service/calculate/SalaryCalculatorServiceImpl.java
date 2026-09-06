package com.bilald.crudsample.service.calculate;

import com.bilald.crudsample.model.GetSalaryInfoDetail;
import org.springframework.stereotype.Service;

@Service
public class SalaryCalculatorServiceImpl implements SalaryCalculatorService {

    private final RoleSalaryCalculatorRegistry roleSalaryCalculatorRegistry;

    public SalaryCalculatorServiceImpl(RoleSalaryCalculatorRegistry roleSalaryCalculatorRegistry) {
        this.roleSalaryCalculatorRegistry = roleSalaryCalculatorRegistry;
    }


    @Override
    public double calculateSalary(GetSalaryInfoDetail getSalaryInfo) {

        return roleSalaryCalculatorRegistry.getRoleSalaryCalculator(getSalaryInfo.getEmployeeRole())
                .calculateSalary(getSalaryInfo);
    }

}
