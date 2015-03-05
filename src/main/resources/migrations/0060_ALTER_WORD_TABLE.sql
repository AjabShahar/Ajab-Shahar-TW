--liquibase formatted sql

--changeset Claxton:60

ALTER TABLE word ADD COLUMN meaning TEXT;
