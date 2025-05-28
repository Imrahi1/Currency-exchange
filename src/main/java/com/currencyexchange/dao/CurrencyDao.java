package com.currencyexchange.dao;

import com.currencyexchange.model.Currency;
import java.util.List;

public interface CurrencyDao {
    void addCurrency(Currency currency);
    Currency getCurrency();
    List<Currency> getAllCurrencies();
}