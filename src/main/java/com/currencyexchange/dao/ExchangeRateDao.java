package com.currencyexchange.dao;

import com.currencyexchange.model.ExchangeRate;

import java.util.List;

public interface ExchangeRateDao {
    List<ExchangeRate> getAllExchangeRates();
    ExchangeRate getExchangeRate(ExchangeRate exchangeRate);
    void addExchangeRate(ExchangeRate exchangeRate);
    void updateExchangeRate(ExchangeRate exchangeRate);

}
