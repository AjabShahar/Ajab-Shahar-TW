--liquibase formatted sql 

--changeset sswaroop:61
ALTER TABLE SONG DROP COLUMN ABOUT;
ALTER TABLE SONG ADD COLUMN ABOUT TEXT;
