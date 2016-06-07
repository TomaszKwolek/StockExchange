SET SQL_SAFE_UPDATES=0;
SET FOREIGN_KEY_CHECKS=0;

DELETE FROM cash_portfolio;
DELETE FROM currency_rate;
DELETE FROM player;
DELETE FROM simulations_parameters;
DELETE FROM stock_portfolio;
DELETE FROM stock_prices;

INSERT INTO simulations_parameters (id, start_date, stop_date, stock_price_factor, currency_price_factor, stock_availibity_factor) VALUES (1, '2013-02-14', '2013-07-17', 2, 2, 2);

INSERT INTO player (id, pesel, first_name, last_name) VALUES (1, '653829128', 'Jan', 'Kowalski');

INSERT INTO cash_portfolio (id, player_id, currency_code, amount) VALUE (1, 1, 'PLN', 10000);























