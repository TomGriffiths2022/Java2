package org.example.inheritance.implementationswap;

import java.math.BigDecimal;

public interface ExchangeRateService {

    BigDecimal getExchangeRate(String fromCurrency, String toCurrency);
}
