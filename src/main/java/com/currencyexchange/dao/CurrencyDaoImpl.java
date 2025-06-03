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
    public Currency getCurrency(String code) {
        Currency currency = null;
        String sql = "select * from Currencies where code = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, code.toUpperCase());
            try (ResultSet rs = pstmt.executeQuery()){
                if (rs.next()){
                    currency = new Currency(
                            rs.getInt("ID"),
                            rs.getString("Code"),
                            rs.getString("FullName"),
                            rs.getString("Sign")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching currency by code: " + code, e);
        }
        return currency;
    }

    @Override
    public void addCurrency(Currency currency) {
        String sql = "INSERT INTO Currencies (Code, FullName, Sign) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, currency.getCode());
            pstmt.setString(2, currency.getFullName());
            pstmt.setString(3, currency.getSign());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding currency", e);
        }
    }

    @Override
    public List<Currency> getAllCurrencies() {
        List<Currency> currencies = new ArrayList<>();
        String sql = "select * from Currencies";

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()){
                Currency currency = new Currency(
                        rs.getInt("ID"),
                        rs.getString("Code"),
                        rs.getString("FullName"),
                        rs.getString("Sign")
                );
                currencies.add(currency);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all currencies: ", e);
        }
        return currencies;
    }
}
