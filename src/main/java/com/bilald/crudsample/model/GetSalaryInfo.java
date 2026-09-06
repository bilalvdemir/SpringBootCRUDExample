package com.bilald.crudsample.model;


import com.bilald.crudsample.common.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GetSalaryInfo {

    private String username;
    private Currency currency;
}
