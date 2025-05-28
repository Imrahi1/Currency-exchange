INSERT INTO Currencies (code, fullname, sign)
VALUES ('EUR', 'Euro', '€'),
       ('USD', 'US Dollar', '$'),
       ('RUB', 'Russian Ruble', '₽'),
       ('CNY', 'Yuan Renminbi', '¥');

INSERT INTO ExchangeRates (BaseCurrencyId, TargetCurrencyId, Rate)
VALUES (
           (SELECT ID FROM Currencies WHERE Code = 'EUR'),
           (SELECT ID FROM Currencies WHERE Code = 'RUB'),
           98.45
       ),
       (
           (SELECT ID FROM Currencies WHERE Code = 'CNY'),
           (SELECT ID FROM Currencies WHERE Code = 'RUB'),
           11.05
       ),
       (
           (SELECT ID FROM Currencies WHERE Code = 'USD'),
           (SELECT ID FROM Currencies WHERE Code = 'RUB'),
           79.62
       ),
       (
           (SELECT ID FROM Currencies WHERE Code = 'USD'),
           (SELECT ID FROM Currencies WHERE Code = 'CNY'),
           11.05
       ),
       (
           (SELECT ID FROM Currencies WHERE Code = 'USD'),
           (SELECT ID FROM Currencies WHERE Code = 'EUR'),
           0.93
       );