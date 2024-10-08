DROP TABLE IF EXISTS PRICE_ENTITY;
DROP TABLE IF EXISTS BRAND;

CREATE TABLE IF NOT EXISTS BRAND (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255)
);
INSERT INTO BRAND (id, name) VALUES (1, 'ZARA');

CREATE TABLE IF NOT EXISTS PRICE_ENTITY (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    brand_id BIGINT,
    product_id BIGINT,
    price_list INT,
    priority INT,
    price DOUBLE,
    currency VARCHAR(255),
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    FOREIGN KEY (brand_id) REFERENCES BRAND(id)
);
INSERT INTO PRICE_ENTITY (brand_id, product_id, price_list, priority, price, currency, start_date, end_date)
VALUES (1, 35455, 1, 0, 35.50, 'EUR', '2020-06-14T00:00:00', '2020-12-31T23:59:59');
INSERT INTO PRICE_ENTITY (brand_id, product_id, price_list, priority, price, currency, start_date, end_date)
VALUES (1, 35455, 2, 1, 25.45, 'EUR', '2020-06-14T15:00:00', '2020-06-14T18:30:00');
INSERT INTO PRICE_ENTITY (brand_id, product_id, price_list, priority, price, currency, start_date, end_date)
VALUES (1, 35455, 3, 1, 30.50, 'EUR', '2020-06-15T00:00:00', '2020-06-15T11:00:00');
INSERT INTO PRICE_ENTITY (brand_id, product_id, price_list, priority, price, currency, start_date, end_date)
VALUES (1, 35455, 4, 1, 38.95, 'EUR', '2020-06-15T16:00:00', '2020-12-31T23:59:59');
