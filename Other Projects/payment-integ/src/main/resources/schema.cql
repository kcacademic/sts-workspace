CREATE KEYSPACE coc_payments
WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 1};

USE coc_payments;

CREATE TYPE IF NOT EXISTS transaction (
id uuid
);

CREATE TABLE IF NOT EXISTS paymentRecord (
id uuid PRIMARY KEY,
transactions set<FROZEN<transaction>>
);

INSERT INTO paymentRecord (id, transactions) 
values (now(), {{ id : now() }});

SELECT * FROM paymentRecord;

UPDATE paymentRecord 
SET transactions = transactions + {{ id : now() }} WHERE id = aae90430-fbb9-11e9-a936-e176235bcdf6;

DROP TABLE IF EXISTS paymentRecord;

DROP TYPE IF EXISTS transaction;

