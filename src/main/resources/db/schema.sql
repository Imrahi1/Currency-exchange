CREATE TABLE IF NOT EXISTS Currencies (
                                        ID INTEGER PRIMARY KEY AUTOINCREMENT,
                                        Code VARCHAR,
                                        FullName VARCHAR,
                                        Sign VARCHAR
);

CREATE TABLE IF NOT EXISTS ExchangeRates (
                                           ID INTEGER PRIMARY KEY AUTOINCREMENT,
                                           BaseCurrencyId INTEGER,
                                           TargetCurrencyId INTEGER,
                                           Rate DECIMAL(6)
);