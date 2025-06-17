package com.currencyexchange.dao;

import com.currencyexchange.model.ExchangeRate;

import java.util.List;

public interface ExchangeRateDao {
    List<ExchangeRate> getAllExchangeRates();
    ExchangeRate getExchangeRate(int baseCurrencyId, int targetCurrencyId);
    ExchangeRate getExchangeRateById(int id);

    void addExchangeRate(ExchangeRate exchangeRate);
    void updateExchangeRate(ExchangeRate exchangeRate);

}
