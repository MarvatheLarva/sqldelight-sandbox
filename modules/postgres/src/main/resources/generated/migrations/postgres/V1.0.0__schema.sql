CREATE TABLE IF NOT EXISTS sale(
    id       UUID NOT NULL,
    code     VARCHAR(20)  NOT NULL,
    user_id  VARCHAR(20)  NOT NULL,
    status   VARCHAR(20)  NOT NULL,

    PRIMARY KEY (id)
);