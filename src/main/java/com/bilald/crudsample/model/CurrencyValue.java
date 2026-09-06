package com.bilald.crudsample.model;

public enum CurrencyValue {

    TL("TL", 0.021),
    EURO("EURO", 1.16),
    USD("USD", 1);

    private String currency;
    private double globalValue;

    public String getCurrency() {
        return currency;
    }

    public double getGlobalValue() {
        return globalValue;
    }

    CurrencyValue(String currency, double globalValue) {
        this.currency = currency;
        this.globalValue = globalValue;
    }
}
