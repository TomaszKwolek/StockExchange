SET SQL_SAFE_UPDATES=0;
SET FOREIGN_KEY_CHECKS=0;


DELETE FROM stock_prices;

INSERT INTO stock_prices (id, company_code, date, price) VALUES (1, 'PKO', '20130412', '1.45');
INSERT INTO stock_prices (id, company_code, date, price) VALUES (2, 'PKO', '20130417', '3.45');
INSERT INTO stock_prices (id, company_code, date, price) VALUES (3, 'PKO', '20140411', '2.45');
INSERT INTO stock_prices (id, company_code, date, price) VALUES (4, 'PKO', '20161212', '4.45');
























