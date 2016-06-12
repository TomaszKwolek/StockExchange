SET SQL_SAFE_UPDATES=0;
SET FOREIGN_KEY_CHECKS=0;

DELETE FROM cash_portfolio;
DELETE FROM stock_portfolio;
DELETE FROM player;
DELETE FROM stock_prices;

INSERT INTO player (id, pesel, first_name, last_name) VALUES (1, '86031701659', 'Jan', 'Kowalski');

INSERT INTO cash_portfolio (id, player_id, currency_code, amount) VALUE (1, 1, 'PLN', 10000.00);

INSERT INTO stock_prices (id, company_code, date, price) VALUES (1, 'PZU', '20130102', 10.12);
INSERT INTO stock_prices (id, company_code, date, price) VALUES (2, 'PKO', '20130102', 5.09);
INSERT INTO stock_prices (id, company_code, date, price) VALUES (3, 'WBK', '20130102', 3.56);
INSERT INTO stock_prices (id, company_code, date, price) VALUES (4, 'PZU', '20130212', 10.00);
INSERT INTO stock_prices (id, company_code, date, price) VALUES (5, 'PKO', '20130212', 4.69);
INSERT INTO stock_prices (id, company_code, date, price) VALUES (6, 'WBK', '20130212', 2.54);
INSERT INTO stock_prices (id, company_code, date, price) VALUES (7, 'PZU', '20130515', 9.12);
INSERT INTO stock_prices (id, company_code, date, price) VALUES (8, 'PKO', '20130515', 3.12);
INSERT INTO stock_prices (id, company_code, date, price) VALUES (9, 'WBK', '20130515', 2.12);
INSERT INTO stock_prices (id, company_code, date, price) VALUES (10, 'PZU', '20130801', 7.12);
INSERT INTO stock_prices (id, company_code, date, price) VALUES (11, 'PKO', '20130801', 3.12);
INSERT INTO stock_prices (id, company_code, date, price) VALUES (12, 'WBK', '20130801', 8.12);
INSERT INTO stock_prices (id, company_code, date, price) VALUES (13, 'PZU', '20131102', 12.12);
INSERT INTO stock_prices (id, company_code, date, price) VALUES (14, 'PKO', '20131102', 30.12);
INSERT INTO stock_prices (id, company_code, date, price) VALUES (15, 'WBK', '20131102', 11.12);






















