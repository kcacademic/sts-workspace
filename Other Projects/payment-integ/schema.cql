CREATE KEYSPACE coc_payments
	WITH replication = {
		'class':'SimpleStrategy', 
		'replication_factor' : 1
	};

USE coc_payments;

CREATE TYPE IF NOT EXISTS transaction (
	id uuid,
	type text
);

CREATE TABLE IF NOT EXISTS paymentRecord (
	id text,
	idempotency_key text,
	payment_provider text,
	payment_method text,
	description text,
	intent text,
	payer_id text,
	user_id text,
	total text,
	sub_total text,
	shipping text,
	tax text,
	currency text,
	payment_status text,
	transactions set<FROZEN<transaction>>,
	PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS paymentrequest (
    idempotency_key text PRIMARY KEY,
    auth_url text,
    payment_id text,
    payment_status text
)

INSERT INTO paymentRecord (id, idempotency_key, payment_provider, payment_method, description, intent, payer_id, user_id, total, sub_total, shipping, tax, currency, payment_status, transactions) 
VALUES ('id', 'idempotency_key', 'paypal', 'paypal', 'description', 'sale', 'payer_id', 'user_id', '7', '5', '1', '1', 'USD', 'created', {{ id : now(), type : 'create' }});

INSERT INTO paymentRequest (idempotency_key, payment_id, payment_status, auth_url)
VALUES ('idempotency_key', 'id', 'created', 'http://paypal.com/auth');

SELECT * FROM paymentRecord WHERE id = 2aa40790-fcd4-11e9-a936-e176235bcdf6;

UPDATE paymentRecord 
SET transactions = transactions + {{ id : now(), type : 'update' }} WHERE id = aae90430-fbb9-11e9-a936-e176235bcdf6;

SELECT * FROM paymentRecord;
SELECT * FROM paymentRequest;
TRUNCATE paymentRecord;
TRUNCATE paymentRequest;
DESCRIBE TYPE transaction;
DESCRIBE paymentRecord;
DESCRIBE paymentRequest;
DROP TABLE IF EXISTS paymentRequest;
DROP TABLE IF EXISTS paymentRecord;
DROP TYPE IF EXISTS transaction;
DROP KEYSPACE IF EXISTS coc_payments;