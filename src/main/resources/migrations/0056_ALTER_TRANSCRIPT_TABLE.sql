--liquibase formatted sql 

--changeset Padma:56
ALTER TABLE transcript  rename column text to hindi_transcript;
ALTER TABLE transcript add column english_transcript character varying(1500);

