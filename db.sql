drop table users;

CREATE TABLE users
(
    user_id       bigint       NOT NULL,
    user_login    varchar(32)  NOT NULL UNIQUE,
    user_password varchar(32)  NOT NULL,
    user_email    varchar(128) NOT NULL UNIQUE,
    user_role     varchar(16)  NOT NULL,
    user_bill     bigint       NOT NULL NOT NULL,
    PRIMARY KEY (user_id)
);

INSERT INTO users
VALUES (123182241, 'akvitka', '20121997', 'invariant17@gmail.com', 'ADMIN', 12731811248247);
INSERT INTO users
VALUES (112311244, 'test', 'test', 'test@gmail.com', 'USER', 128712741827316);



drop table cards;

CREATE TABLE cards
(
    card_id     bigint NOT NULL,
    user_id     bigint NOT NULL UNIQUE,
    card_sum    bigint NOT NULL,
    bill_id     bigint NOT NULL,
    card_status varchar(26),
    PRIMARY KEY (card_id)
);
drop table bill;
CREATE TABLE bill
(
    bill_id     bigint      NOT NULL,
    bill_sum    bigint      NOT NULL,
    bill_name   varchar(16) NOT NULL,
    bill_status boolean     NOT NULL,

    PRIMARY KEY (bill_id)
);

drop table Transaction_history;
CREATE TABLE Transaction_history
(
    user_id      bigint      NOT NULL,
    tr_id        bigint      NOT NULL,
    bill_id      bigint      NOT NULL,
    card_id      bigint      NOT NULL,
    tr_date      datetime,
    payment_sum  bigint      NOT NULL,
    payment_type varchar(16) NOT NULL,
    tr_status    varchar(16) NOT NULL,
    payment_id   bigint      NOT NULL,
    PRIMARY KEY (tr_id)
);

drop table payments;

CREATE TABLE payments
(
    user_id        bigint      NOT NULL,
    payment_id     bigint      NOT NULL UNIQUE,
    bill_id        bigint      NOT NULL,
    card_id        bigint      NOT NULL,
    payment_sum    bigint      NOT NULL,
    payment_type   varchar(16) NOT NULL,
    payment_status int         NOT NULL,
    PRIMARY KEY (payment_id)
);