package com.currencyexchange.dao;

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
    public ExchangeRate getExchangeRate(int baseCurrencyId, int targetCurrencyId) {
        ExchangeRate exchangeRateFromDb = null;
        String sql = "select * from ExchangeRates where BaseCurrencyId=? and TargetCurrencyId=?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, baseCurrencyId);
            pstmt.setInt(2, targetCurrencyId);
            try (ResultSet rs = pstmt.executeQuery()){
                if (rs.next()){
                    exchangeRateFromDb = mapExchangeRateFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching exchange rate: ", e);
        }

        return exchangeRateFromDb;
    }

    @Override
    public ExchangeRate getExchangeRateById(int id) {
        ExchangeRate exchangeRateFromDb = null;
        String sql = "select * from ExchangeRates where ID=?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()){
                if (rs.next()){
                    exchangeRateFromDb = mapExchangeRateFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching exchange rate: ", e);
        }

        return exchangeRateFromDb;
    }

    @Override
    public void addExchangeRate(ExchangeRate exchangeRate) {
        String sql = "INSERT INTO ExchangeRates (BaseCurrencyId, TargetCurrencyId, Rate) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, exchangeRate.getBaseCurrencyId());
            pstmt.setInt(2, exchangeRate.getTargetCurrencyId());
            pstmt.setDouble(3, exchangeRate.getRate());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding exchange rate: ", e);
        }
    }

    @Override
    public void updateExchangeRate(ExchangeRate exchangeRate) {
        String sql = "UPDATE ExchangeRates SET BaseCurrencyId=?, TargetCurrencyId=?, Rate=? WHERE ID=?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, exchangeRate.getBaseCurrencyId());
            pstmt.setInt(2, exchangeRate.getTargetCurrencyId());
            pstmt.setDouble(3, exchangeRate.getRate());
            pstmt.setInt(4, exchangeRate.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding exchange rate: ", e);
        }
    }

    private ExchangeRate mapExchangeRateFromResultSet(ResultSet rs) throws SQLException {
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setId(rs.getInt("ID"));
        exchangeRate.setBaseCurrencyId(rs.getInt("BaseCurrencyId"));
        exchangeRate.setTargetCurrencyId(rs.getInt("TargetCurrencyId"));
        exchangeRate.setRate(rs.getDouble("Rate"));
        return exchangeRate;
    }
}
