--liquibase formatted sql

--changeset JAIDEEP:1
CREATE TABLE PEOPLE (
    ID SERIAL,
    NAME VARCHAR(100) NOT NULL
);

--rollback drop table PERSON;