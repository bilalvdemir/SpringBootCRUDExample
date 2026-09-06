package com.bilald.crudsample.service.currency;

import com.bilald.crudsample.common.enums.Currency;

public interface CurrencyConverterService {

    double convert(double amount, Currency from, Currency to);
}
