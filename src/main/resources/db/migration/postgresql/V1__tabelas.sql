CREATE TABLE transactions (
    id int8 NOT NULL,
    description varchar(100) NOT NULL,
    CONSTRAINT transactions_pk PRIMARY KEY (id)
);

create sequence transactions_seq increment 1 owned by transactions.id;


CREATE TABLE transactions_copy (
    id int8 NOT NULL,
    description varchar(100) NOT NULL,
    CONSTRAINT transactions_copy_pk PRIMARY KEY (id)
);

create sequence transactions_copy_seq increment 1 owned by transactions_copy.id;