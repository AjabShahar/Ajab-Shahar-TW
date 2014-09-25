--liquibase formatted sql

--changeset JAIDEEP:11
CREATE TABLE NOTE (
    ID SERIAL,
    TEXT_IN_ENGLISH_TRANSLITERATION VARCHAR(500) NOT NULL,
);
--rollback drop table COUPLET_NOTE;