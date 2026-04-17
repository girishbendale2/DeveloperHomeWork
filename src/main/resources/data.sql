CREATE TABLE transaction (
    customer_id INT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    transaction_date DATE NOT NULL
);


INSERT INTO transaction (customer_id, amount, transaction_date) VALUES
('1', 120.00, '2026-01-15'),
('2', 80.00, '2026-01-20'),
('3', 150.00, '2026-02-10'),
('4', 60.00, '2026-02-15'),
('5', 200.00, '2026-03-05'),
('6', 90.00, '2026-01-12'),
('7', 130.00, '2026-02-18'),
('8', 70.00, '2026-03-22');

commit;

