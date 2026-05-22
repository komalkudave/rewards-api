CREATE TABLE customers
(
    id   BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE transactions
(
    id               BIGINT PRIMARY KEY AUTO_INCREMENT,
    customer_id      BIGINT NOT NULL,
    amount DOUBLE NOT NULL,
    transaction_date DATE   NOT NULL
);