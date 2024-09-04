CREATE TABLE customers
(
    id        serial PRIMARY KEY,
    name      varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL
);

CREATE TABLE products
(
    id    serial PRIMARY KEY,
    name  varchar(50) NOT NULL,
    price float8

);

CREATE TABLE purchases
(
    id serial PRIMARY KEY,
    date timestamp NOT NULL,
    customer_id bigint REFERENCES customers(id),
    product_id bigint REFERENCES products(id)
);