--liquibase formatted sql

--changeset sswaroop:30
ALTER TABLE Word DROP COLUMN synonym;
