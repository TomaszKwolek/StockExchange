SET SQL_SAFE_UPDATES=0;
SET FOREIGN_KEY_CHECKS=0;

DELETE FROM cash_portfolio;
DELETE FROM currency_rate;
DELETE FROM player;
DELETE FROM stock_portfolio;
DELETE FROM stock_prices;

INSERT INTO player (id, pesel, first_name, last_name) VALUES (1, '86031701659', 'Jan', 'Kowalski');

INSERT INTO cash_portfolio (id, player_id, currency_code, amount) VALUE (1, 1, 'PLN', 10000);

INSERT INTO stock_prices (id, company_code, date, price) VALUES (1, 'PZU', '20130102', 10.12);
INSERT INTO stock_prices (id, company_code, date, price) VALUES (2, 'PKO', '20130102', 5.09);
INSERT INTO stock_prices (id, company_code, date, price) VALUES (3, 'WBK', '20130102', 3.56);
INSERT INTO stock_prices (id, company_code, date, price) VALUES (4, 'ING', '20130102', 10.00);
INSERT INTO stock_prices (id, company_code, date, price) VALUES (5, 'MBANK', '20130102', 4.69);
INSERT INTO stock_prices (id, company_code, date, price) VALUES (6, 'ALR', '20130102', 2.54);




















