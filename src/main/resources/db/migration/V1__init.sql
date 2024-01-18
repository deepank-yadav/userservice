CREATE TABLE user
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    is_deleted   BIT(1) NOT NULL,
    created_at   datetime NULL,
    last_updated datetime NULL,
    email        VARCHAR(255) NULL,
    username     VARCHAR(255) NULL,
    password     VARCHAR(255) NULL,
    name         VARCHAR(255) NULL,
    address      VARCHAR(255) NULL,
    phone        VARCHAR(255) NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);