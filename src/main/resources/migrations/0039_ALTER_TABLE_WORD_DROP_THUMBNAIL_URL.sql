--liquibase formatted sql

--changeset sswaroop:39
ALTER TABLE Word DROP COLUMN thumbnail_url;
