package com.currencyexchange.dao;

import com.currencyexchange.model.Currency;
import com.currencyexchange.model.ExchangeRate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class ExchangeRateDaoImpl implements ExchangeRateDao {
    private final Connection connection;

    public ExchangeRateDaoImpl() {
        this.connection = DbConnection.getConnection();
    }

    @Override
    public List<ExchangeRate> getAllExchangeRates() {
        List<ExchangeRate> exchangeRates = new ArrayList<>();
        String sql = "select * from ExchangeRates";

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()){
                exchangeRates.add(mapExchangeRateFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all exchange rates: ", e);
        }
        return exchangeRates;
    }

    @Override
    public ExchangeRate getExchangeRate(ExchangeRate exchangeRate) {
        return null;
    }

    @Override
    public void addExchangeRate(ExchangeRate exchangeRate) {

    }

    @Override
    public void updateExchangeRate(ExchangeRate exchangeRate) {

    }

    public ExchangeRate mapExchangeRateFromResultSet(ResultSet rs) throws SQLException {
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setId(rs.getInt("ID"));
        exchangeRate.setBaseCurrencyId(rs.getInt("Code"));
        exchangeRate.setTargetCurrencyId(rs.getInt("FullName"));
        exchangeRate.setRate(rs.getFloat("Sign"));
        return exchangeRate;
    }
}
