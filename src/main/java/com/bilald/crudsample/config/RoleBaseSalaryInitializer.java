package com.bilald.crudsample.config;

import com.bilald.crudsample.common.enums.Currency;
import com.bilald.crudsample.common.enums.EmployeeRole;
import com.bilald.crudsample.model.RoleBaseSalary;
import com.bilald.crudsample.repository.RoleBaseSalaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Slf4j
public class RoleBaseSalaryInitializer implements ApplicationRunner {

    private final RoleBaseSalaryRepository roleBaseSalaryRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (roleBaseSalaryRepository.count() == 0) {
            log.info("Initializing default role base salaries...");

            Arrays.stream(EmployeeRole.values()).forEach(role -> {
                RoleBaseSalary roleBaseSalary = RoleBaseSalary.builder()
                        .role(role)
                        .baseSalary(getDefaultBaseSalary(role))
                        .currency(Currency.USD)
                        .build();

                roleBaseSalaryRepository.save(roleBaseSalary);
                log.info("Created default base salary for role {}: {} USD", role, roleBaseSalary.getBaseSalary());
            });

            log.info("Default role base salaries initialized successfully.");
        } else {
            log.info("Role base salaries already exist, skipping initialization.");
        }
    }

    private double getDefaultBaseSalary(EmployeeRole role) {
        return switch (role) {
            case DIRECTOR -> 10000.0;
            case MANAGER -> 5000.0;
            case STAFF -> 2000.0;
        };
    }
}
