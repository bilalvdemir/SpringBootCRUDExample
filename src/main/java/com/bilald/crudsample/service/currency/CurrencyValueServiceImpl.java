package com.bilald.crudsample.service.currency;


import com.bilald.crudsample.common.enums.Currency;
import com.bilald.crudsample.model.CurrencyValue;
import org.springframework.stereotype.Service;

@Service
public class CurrencyValueServiceImpl implements CurrencyValueService {


    @Override
    public double getCurrencyValue(Currency currency) {
        // todo: here external currency api integration is required. Now we will continue with enum value mock datas.
        return CurrencyValue.valueOf(currency.name()).getGlobalValue();
    }
}
