package com.bilald.crudsample.service.calculate;

import com.bilald.crudsample.model.GetSalaryInfoDetail;
import com.bilald.crudsample.model.SalaryInfo;

public interface SalaryCalculatorService {

    double calculateSalary(GetSalaryInfoDetail getSalaryInfo);
}
