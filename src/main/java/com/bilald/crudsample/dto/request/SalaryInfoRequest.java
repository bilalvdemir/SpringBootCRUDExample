package com.bilald.crudsample.dto.request;

import com.bilald.crudsample.common.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalaryInfoRequest {

    private String username;
    private Currency currency;
}
