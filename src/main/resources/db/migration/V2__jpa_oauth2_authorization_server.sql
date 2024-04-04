CREATE UNIQUE INDEX IX_pk_authorizationconsent ON authorization_consent (registered_client_id, principal_name);

ALTER TABLE authorization
    MODIFY access_token_metadata LONGTEXT;

ALTER TABLE authorization
    MODIFY access_token_scopes LONGTEXT;

ALTER TABLE authorization
    MODIFY access_token_value LONGTEXT;

ALTER TABLE authorization
    MODIFY attributes LONGTEXT;

ALTER TABLE authorization
    MODIFY authorization_code_value LONGTEXT;

ALTER TABLE authorization
    MODIFY authorized_scopes LONGTEXT;

ALTER TABLE authorization
    MODIFY device_code_metadata LONGTEXT;

ALTER TABLE authorization
    MODIFY device_code_value LONGTEXT;

ALTER TABLE authorization
    MODIFY oidc_id_token_claims LONGTEXT;

ALTER TABLE authorization
    MODIFY oidc_id_token_metadata LONGTEXT;

ALTER TABLE authorization
    MODIFY oidc_id_token_value LONGTEXT;

ALTER TABLE authorization
    MODIFY refresh_token_metadata LONGTEXT;

ALTER TABLE authorization
    MODIFY refresh_token_value LONGTEXT;

ALTER TABLE authorization
    MODIFY state LONGTEXT;

ALTER TABLE authorization
    MODIFY user_code_metadata LONGTEXT;

ALTER TABLE authorization
    MODIFY user_code_value LONGTEXT;