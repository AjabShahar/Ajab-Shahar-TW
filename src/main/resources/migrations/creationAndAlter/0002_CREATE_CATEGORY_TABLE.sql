--liquibase formatted sql

--changeset PADMA:2
CREATE TABLE CATEGORY (
    ID SERIAL PRIMARY KEY,
    NAME VARCHAR(100) NOT NULL
);
