package org.example.inheritance.implementationswap;

import java.math.BigDecimal;

public class CurrencyConverter {

    // ExchangeEateService is the dependency
    // CurrencyConverter is the dependent
    // You should pass essential dependencies in via the constructor
    // Your dependencies SHOULD be abstract not concrete
    private ExchangeRateService service;

    public CurrencyConverter(ExchangeRateService service) {
        this.service = service;
    }
    public BigDecimal convertCurrency(BigDecimal amount, String fromCurrency, String toCurrency) {
        // creating one worker inside another is a code smell; instead we should pass the worker in
        // var service = new ExchangeRateService();
        var exchangeRate = service.getExchangeRate(fromCurrency, toCurrency);
        // TODO
        return null;
    }
}
