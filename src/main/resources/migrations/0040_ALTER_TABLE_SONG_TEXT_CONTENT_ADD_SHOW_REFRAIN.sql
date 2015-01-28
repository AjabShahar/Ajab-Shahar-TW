--liquibase formatted sql

--changeset sswaroop:40
ALTER TABLE SONG_TEXT_CONTENT ADD show_refrain boolean;
