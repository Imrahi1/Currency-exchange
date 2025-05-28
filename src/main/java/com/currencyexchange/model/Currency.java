package com.currencyexchange.model;

public class Currency {
    private int id;
    private String code;
    private String fullName;
    private String sign;

    // Конструкторы
    public Currency() {}

    public Currency(int id, String code, String fullName, String sign) {
        this.id = id;
        this.code = code;
        this.fullName = fullName;
        this.sign = sign;
    }

    // Геттеры и сеттеры
    // ...
}
