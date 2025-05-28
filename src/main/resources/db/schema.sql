CREATE TABLE IF NOT EXISTS Currencies (
                                        ID INTEGER PRIMARY KEY AUTOINCREMENT,
                                        Code VARCHAR NOT NULL,
                                        FullName VARCHAR NOT NULL,
                                        Sign VARCHAR NOT NULL
);
-- Таблица обменных курсов
CREATE TABLE IF NOT EXISTS ExchangeRates (
                                             ID INTEGER PRIMARY KEY AUTOINCREMENT,
                                             BaseCurrencyId INTEGER NOT NULL,
                                             TargetCurrencyId INTEGER NOT NULL,
                                             Rate DECIMAL(6) NOT NULL,

    -- Внешний ключ для базовой валюты
                                             FOREIGN KEY (BaseCurrencyId)
                                                 REFERENCES Currencies(ID)
                                                 ON DELETE CASCADE,

    -- Внешний ключ для целевой валюты
                                             FOREIGN KEY (TargetCurrencyId)
                                                 REFERENCES Currencies(ID)
                                                 ON DELETE CASCADE,

    -- Уникальная пара валют
                                             UNIQUE (BaseCurrencyId, TargetCurrencyId)
);