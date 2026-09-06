package com.bilald.crudsample.service.currency;

import com.bilald.crudsample.common.enums.Currency;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyConverterServiceImpl implements CurrencyConverterService {

    private final CurrencyValueService currencyValueService;

    @Override
    public double convert(double amount, Currency from, Currency to) {
        double fromValue = currencyValueService.getCurrencyValue(from);
        double toValue = currencyValueService.getCurrencyValue(to);
        return amount * fromValue / toValue;
    }
}
