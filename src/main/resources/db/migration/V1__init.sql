CREATE TABLE `role`
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    deleted      BIT(1) NOT NULL,
    created_at   datetime NULL,
    last_updated datetime NULL,
    `role`       VARCHAR(255) NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE token
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    deleted      BIT(1) NOT NULL,
    created_at   datetime NULL,
    last_updated datetime NULL,
    value        VARCHAR(255) NULL,
    user_id      BIGINT NULL,
    expiry_at    datetime NULL,
    CONSTRAINT pk_token PRIMARY KEY (id)
);

CREATE TABLE user
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    deleted       BIT(1) NOT NULL,
    created_at    datetime NULL,
    last_updated  datetime NULL,
    email         VARCHAR(255) NULL,
    username      VARCHAR(255) NULL,
    hash_password VARCHAR(255) NULL,
    name          VARCHAR(255) NULL,
    phone         VARCHAR(255) NULL,
    street        VARCHAR(255) NULL,
    city          VARCHAR(255) NULL,
    zipcode       VARCHAR(255) NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE user_role
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL
);

ALTER TABLE token
    ADD CONSTRAINT FK_TOKEN_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE user_role
    ADD CONSTRAINT fk_user_role_on_role FOREIGN KEY (role_id) REFERENCES `role` (id);

ALTER TABLE user_role
    ADD CONSTRAINT fk_user_role_on_user FOREIGN KEY (user_id) REFERENCES user (id);