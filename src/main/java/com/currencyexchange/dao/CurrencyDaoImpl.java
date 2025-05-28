package com.currencyexchange.dao;

import com.currencyexchange.model.Currency;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class CurrencyDaoImpl implements CurrencyDao {
    private final Connection connection;

    public CurrencyDaoImpl() {
        this.connection = DbConnection.getConnection();
    }

    @Override
    public Currency getCurrency() {
        Currency currency = null;
        String sql = "select * from Currencies where code = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery(sql)) {
            currency = new Currency(
                    rs.getInt(0),
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3)
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error adding transaction", e);
        }
        return currency;
    }

    @Override
    public void addCurrency(Currency currency) {

    }

    @Override
    public List<Currency> getAllCurrencies() {
        return List.of();
    }
}
