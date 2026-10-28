CREATE TABLE IF NOT EXISTS payments (
 id UUID PRIMARY KEY,
 user_id UUID NOT NULL,
 plan_code VARCHAR(50) NOT NULL,
 amount_paise BIGINT NOT NULL CHECK (amount_paise > 0),
 currency VARCHAR(3) NOT NULL DEFAULT 'INR',
 receipt VARCHAR(100) NOT NULL UNIQUE,
 razorpay_order_id VARCHAR(100) NOT NULL UNIQUE,
 razorpay_payment_id VARCHAR(100) UNIQUE,
 status VARCHAR(30) NOT NULL,
 created_at TIMESTAMPTZ NOT NULL,
 updated_at TIMESTAMPTZ NOT NULL
);
CREATE INDEX IF NOT EXISTS idx_payments_user_id ON payments(user_id);
