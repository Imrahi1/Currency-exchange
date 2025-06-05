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
    public Currency getCurrency(int id) {
        Currency currency = null;
        String sql = "select * from Currencies where id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()){
                if (rs.next()){
                    return mapCurrencyFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching currency by id: " + id, e);
        }
        return currency;
    }

    @Override
    public Currency getCurrency(String code) {
        Currency currency = new Currency();
        String sql = "select * from Currencies where code = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, code.toUpperCase());
            try (ResultSet rs = pstmt.executeQuery()){
                if (rs.next()){
                    return mapCurrencyFromResultSet(rs);
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
                currencies.add(mapCurrencyFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all currencies: ", e);
        }
        return currencies;
    }

    public Currency mapCurrencyFromResultSet(ResultSet rs) throws SQLException {
        Currency currency = new Currency();
        currency.setId(rs.getInt("ID"));
        currency.setCode(rs.getString("Code"));
        currency.setFullName(rs.getString("FullName"));
        currency.setSign(rs.getString("Sign"));
        return currency;
    }
}
