DROP TABLE IF EXISTS sales_periods;
DROP TABLE IF EXISTS product;

CREATE TABLE product
(
    id integer not null,
    name varchar(2000),
    CONSTRAINT product_pkey PRIMARY KEY (id)
);

CREATE TABLE sales_periods
(
    id INTEGER NOT NULL,
    price bigint not null,
    date_from date not null,
    date_to date,
    product integer not null,
    constraint sales_periods_pkey PRIMARY KEY (id)
);