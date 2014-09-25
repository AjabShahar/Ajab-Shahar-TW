--liquibase formatted sql

--changeset JAIDEEP:4
CREATE TABLE PERSON (
    ID SERIAL,
    NAME VARCHAR(100) NOT NULL,
    DESIGNATION VARCHAR(400) NOT NULL
);
--rollback drop table PERSON;