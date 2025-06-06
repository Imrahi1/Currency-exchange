package com.currencyexchange.model;

public class ExchangeResult {
    private int amount;
    private int convertedAmount;

    public ExchangeResult() {
    }
    public ExchangeResult(int amount, int convertedAmount) {
        this.amount = amount;
        this.convertedAmount = convertedAmount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(int convertedAmount) {
        this.convertedAmount = convertedAmount;
    }
}
