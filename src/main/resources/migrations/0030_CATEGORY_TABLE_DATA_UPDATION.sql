--liquibase formatted sql

--changeset PADMA:30
UPDATE CATEGORY SET NAME='Word intro' where CATEGORY_TYPE = 'word'