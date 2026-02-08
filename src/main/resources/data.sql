-- stores
INSERT INTO stores (id, store_code, name, status) VALUES (1, 'ST-001', 'KPOS Gangnam', 'ACTIVE');
INSERT INTO stores (id, store_code, name, status) VALUES (2, 'ST-002', 'KPOS Hongdae', 'ACTIVE');

-- terminals
INSERT INTO terminals (id, terminal_code, store_id, status) VALUES (1, 'TM-001', 1, 'ACTIVE');
INSERT INTO terminals (id, terminal_code, store_id, status) VALUES (2, 'TM-002', 1, 'ACTIVE');
INSERT INTO terminals (id, terminal_code, store_id, status) VALUES (3, 'TM-003', 2, 'INACTIVE');

-- payment_transactions
INSERT INTO payment_transactions
(id, tx_id, store_id, terminal_id, amount, currency, method, status, buyer_id, description, requested_at, approved_at)
VALUES
    (1, 'TX-20260208-0001', 1, 1, 12000, 'KRW', 'CARD', 'REQUESTED', 'userA', 'latte', CURRENT_TIMESTAMP, NULL);

INSERT INTO payment_transactions
(id, tx_id, store_id, terminal_id, amount, currency, method, status, buyer_id, description, requested_at, approved_at)
VALUES
    (2, 'TX-20260208-0002', 1, 2, 55000, 'KRW', 'TOSSPAY', 'APPROVED', 'userB', 'sandwich set', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO payment_transactions
(id, tx_id, store_id, terminal_id, amount, currency, method, status, buyer_id, description, requested_at, approved_at)
VALUES
    (3, 'TX-20260208-0003', 2, NULL, 3300, 'KRW', 'APPLEPAY', 'FAILED', 'userC', 'americano', CURRENT_TIMESTAMP, NULL);
