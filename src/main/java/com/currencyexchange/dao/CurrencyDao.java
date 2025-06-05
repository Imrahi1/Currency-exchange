package com.currencyexchange.dao;

import com.currencyexchange.model.Currency;
import java.util.List;

public interface CurrencyDao {
    void addCurrency(Currency currency);
    Currency getCurrency(String code);
    Currency getCurrency(int id);
    List<Currency> getAllCurrencies();
}