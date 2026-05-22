INSERT INTO customers(name)
VALUES ('John');

INSERT INTO customers(name)
VALUES ('Alice');

-- Transactions within last 3 months

INSERT INTO transactions(customer_id, amount, transaction_date)
VALUES (1, 120.0, CURRENT_DATE - 10);

INSERT INTO transactions(customer_id, amount, transaction_date)
VALUES (1, 75.0, CURRENT_DATE - 40);

INSERT INTO transactions(customer_id, amount, transaction_date)
VALUES (2, 200.0, CURRENT_DATE - 70);

-- Older than 3 months (should NOT be included)

INSERT INTO transactions(customer_id, amount, transaction_date)
VALUES (1, 300.0, CURRENT_DATE - 150);

INSERT INTO transactions(customer_id, amount, transaction_date)
VALUES (2, 500.0, CURRENT_DATE - 200);