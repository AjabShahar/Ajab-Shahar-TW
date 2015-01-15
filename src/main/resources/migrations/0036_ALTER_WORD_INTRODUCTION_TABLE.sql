--liquibase formatted sql

--changeset sswaroop:36
ALTER TABLE word_introduction ALTER COLUMN introduction_text type  varchar(600);