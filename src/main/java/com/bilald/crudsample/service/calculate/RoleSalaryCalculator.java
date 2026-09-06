package com.bilald.crudsample.service.calculate;

import com.bilald.crudsample.common.enums.EmployeeRole;
import com.bilald.crudsample.model.GetSalaryInfoDetail;

public interface RoleSalaryCalculator {

    EmployeeRole getEmployeeRole();

    double calculateSalary(GetSalaryInfoDetail salaryInfoDetail);
}
