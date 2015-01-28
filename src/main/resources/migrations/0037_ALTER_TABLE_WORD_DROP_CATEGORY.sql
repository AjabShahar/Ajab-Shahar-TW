--liquibase formatted sql

--changeset sswaroop:37
ALTER TABLE WORD DROP CONSTRAINT word_category_id_fkey;
ALTER TABLE Word DROP COLUMN category_id;
