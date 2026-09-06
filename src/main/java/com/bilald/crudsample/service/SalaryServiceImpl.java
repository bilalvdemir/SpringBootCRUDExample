package com.bilald.crudsample.service;

import com.bilald.crudsample.exception.NotEmployeeUserException;
import com.bilald.crudsample.exception.UserNotFoundException;
import com.bilald.crudsample.mapper.EmployeeMapper;
import com.bilald.crudsample.model.GetSalaryInfo;
import com.bilald.crudsample.model.GetSalaryInfoDetail;
import com.bilald.crudsample.model.SalaryInfo;
import com.bilald.crudsample.service.calculate.SalaryCalculatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class SalaryServiceImpl implements SalaryService {

    private final EmployeeService employeeService;
    private final UserService userService;
    private final EmployeeMapper employeeMapper;
    private final SalaryCalculatorService salaryCalculatorService;

    public SalaryServiceImpl(EmployeeService employeeService, UserService userService, EmployeeMapper employeeMapper, SalaryCalculatorService salaryCalculatorService) {
        this.salaryCalculatorService = salaryCalculatorService;
        this.employeeService = employeeService;
        this.userService = userService;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public SalaryInfo getSalaryInfo(GetSalaryInfo getSalaryInfo) {
        var username = getSalaryInfo.getUsername();
        log.info("[getSalaryInfo] salary info get for: {}", username);
        return Optional.ofNullable(employeeService.getEmployeeByUsername(username))
                .map(employee -> {
                    double salary = salaryCalculatorService.calculateSalary(GetSalaryInfoDetail.builder()
                            .employeeRole(employee.getRole())
                            .currency(getSalaryInfo.getCurrency())
                            .createdAt(employee.getCreatedAt())
                            .employeeRole(employee.getRole()).build());
                    return employeeMapper.toSalaryInfo(employee, salary);
                })
                .orElseThrow(() -> {
                    if (userService.getUserByUsername(username) != null) {
                        return new NotEmployeeUserException(username);
                    }
                    return new UserNotFoundException(username);
                });
    }
}
