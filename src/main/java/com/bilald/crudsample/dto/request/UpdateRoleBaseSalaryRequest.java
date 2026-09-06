package com.bilald.crudsample.dto.request;

import com.bilald.crudsample.common.enums.Currency;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRoleBaseSalaryRequest {

    @Positive(message = "Base salary must be positive")
    private Double baseSalary;

    private Currency currency;
}
