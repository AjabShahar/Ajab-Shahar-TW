--liquibase formatted sql

--changeset sswaroop:35
ALTER TABLE Word DROP COLUMN synonym;
