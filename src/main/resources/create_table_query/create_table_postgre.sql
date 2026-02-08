-- Stores Table
create table stores (
  id bigserial primary key,
  store_code varchar(40) not null unique,
  name varchar(100) not null,
  status varchar(20) not null,
  created_at timestamptz not null,
  updated_at timestamptz not null
);

-- Terminals Table
create table terminals (
  id bigserial primary key,
  terminal_code varchar(40) not null unique,
  store_id bigint not null references stores(id),
  status varchar(20) not null,
  created_at timestamptz not null,
  updated_at timestamptz not null
);

-- Payment Transactions Table
create table payment_transactions (
  id bigserial primary key,
  tx_id varchar(60) not null unique,
  store_id bigint not null references stores(id),
  terminal_id bigint references terminals(id),
  amount bigint not null,
  currency varchar(10) not null,
  method varchar(20) not null,
  status varchar(20) not null,
  buyer_id varchar(60),
  description varchar(200),
  requested_at timestamptz not null,
  approved_at timestamptz,
  created_at timestamptz not null,
  updated_at timestamptz not null
);

-- Indexes
create index idx_tx_requested_at on payment_transactions(requested_at desc);
create index idx_tx_store_status on payment_transactions(store_id, status);
create index idx_tx_method on payment_transactions(method);
