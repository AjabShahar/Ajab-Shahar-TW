--liquibase formatted sql 

--changeset CHANAKYA:65

ALTER TABLE gathering RENAME COLUMN original_text TO hindi_text;