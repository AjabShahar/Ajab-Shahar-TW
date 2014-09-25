--liquibase formatted sql

--changeset JAIDEEP:5
CREATE TABLE GENRE (
    ID SERIAL,
    NAME VARCHAR(100) NOT NULL,
    DESCRIPTION VARCHAR(400) NOT NULL
);
--rollback drop table GENRE;