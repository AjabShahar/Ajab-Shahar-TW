--liquibase formatted sql

--changeset PADMA:11

ALTER TABLE PERSON ALTER COLUMN LAST_NAME DROP NOT NULL;

