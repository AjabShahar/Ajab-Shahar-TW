--liquibase formatted sql

--changeset Claxton:58

ALTER TABLE word ADD COLUMN diacritic CHARACTER VARYING(100);
ALTER TABLE word ADD COLUMN is_root_word boolean DEFAULT FALSE;
