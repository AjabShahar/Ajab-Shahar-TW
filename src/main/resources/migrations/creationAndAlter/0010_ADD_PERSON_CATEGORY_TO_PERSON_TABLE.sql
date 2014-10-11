--liquibase formatted sql

--changeset PADMA:10

ALTER TABLE PERSON ADD COLUMN  CATEGORY varchar(200) NOT NULL;