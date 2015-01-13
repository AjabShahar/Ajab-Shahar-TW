--liquibase formatted sql

--changeset padma:36
ALTER TABLE word_introduction ALTER COLUMN introduction_text type  varchar(600);