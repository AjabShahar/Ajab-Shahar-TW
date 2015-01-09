--liquibase formatted sql

--changeset sswaroop:34
ALTER TABLE Word DROP COLUMN thumbnail_url;
