INSERT INTO product (id, name) VALUES (1, 'car_test');
INSERT INTO product (id, name) VALUES (2, 'bike_test');

INSERT INTO sales_periods (id, price, date_from, date_to, product) VALUES (1, 100, '2020-05-01', '2020-05-04', 1);
INSERT INTO sales_periods (id, price, date_from, date_to, product) VALUES (2, 150, '2020-05-03', '2020-05-08', 1);
INSERT INTO sales_periods (id, price, date_from, date_to, product) VALUES (4, 50, '2020-05-02', '2020-05-07', 2);
INSERT INTO sales_periods (id, price, date_from, date_to, product) VALUES (5, 60, '2020-05-09', null, 2);
INSERT INTO sales_periods (id, price, date_from, date_to, product) VALUES (3, 120, '2020-05-31', '2020-06-04', 1);
