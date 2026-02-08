-- Stores Table
CREATE TABLE stores (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  store_code VARCHAR(40) NOT NULL UNIQUE,
  name VARCHAR(100) NOT NULL,
  status VARCHAR(20) NOT NULL,
  created_at DATETIME(6) NOT NULL,
  updated_at DATETIME(6) NOT NULL
) ENGINE=InnoDB;

-- Terminals Table
CREATE TABLE terminals (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  terminal_code VARCHAR(40) NOT NULL UNIQUE,
  store_id BIGINT NOT NULL,
  status VARCHAR(20) NOT NULL,
  created_at DATETIME(6) NOT NULL,
  updated_at DATETIME(6) NOT NULL,
  CONSTRAINT fk_terminal_store FOREIGN KEY (store_id) REFERENCES stores(id)
) ENGINE=InnoDB;

-- Payment Transactions Table
CREATE TABLE payment_transactions (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  tx_id VARCHAR(60) NOT NULL UNIQUE,
  store_id BIGINT NOT NULL,
  terminal_id BIGINT,
  amount BIGINT NOT NULL,
  currency VARCHAR(10) NOT NULL,
  method VARCHAR(20) NOT NULL,
  status VARCHAR(20) NOT NULL,
  buyer_id VARCHAR(60),
  description VARCHAR(200),
  requested_at DATETIME(6) NOT NULL,
  approved_at DATETIME(6),
  created_at DATETIME(6) NOT NULL,
  updated_at DATETIME(6) NOT NULL,
  CONSTRAINT fk_tx_store FOREIGN KEY (store_id) REFERENCES stores(id),
  CONSTRAINT fk_tx_terminal FOREIGN KEY (terminal_id) REFERENCES terminals(id),
  INDEX idx_tx_requested_at (requested_at DESC),
  INDEX idx_tx_store_status (store_id, status),
  INDEX idx_tx_method (method)
) ENGINE=InnoDB;