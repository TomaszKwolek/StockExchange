SET SQL_SAFE_UPDATES=0;
SET FOREIGN_KEY_CHECKS=0;

DELETE FROM cash_portfolio;
DELETE FROM player;

INSERT INTO player (id, pesel, first_name, last_name) VALUES (1, '86031701659', 'Jan', 'Kowalski');

INSERT INTO cash_portfolio (id, player_id, currency_code, amount) VALUE (1, 1, 'PLN', 10000.00);






















