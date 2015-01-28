--liquibase formatted sql

--changeset Padma:45

ALTER TABLE word DROP COLUMN intr_summary_transliteration;
ALTER TABLE word RENAME COLUMN intr_summary_original TO hindi_intro_excerpt;
ALTER TABLE word RENAME COLUMN intr_summary_translation TO english_intro_excerpt;
