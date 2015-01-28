--liquibase formatted sql

--changeset sswaroop:41
ALTER TABLE word_introduction ALTER COLUMN introduction_text type  varchar(600);