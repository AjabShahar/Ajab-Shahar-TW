--liquibase formatted sql

--changeset Padma:41
ALTER TABLE word_introduction DROP COLUMN intro_text_transliteration;
ALTER TABLE word_introduction ALTER COLUMN intro_text_original type varchar(1600);
ALTER TABLE word_introduction ALTER COLUMN intro_text_translation type varchar(1600);
ALTER TABLE word_introduction RENAME COLUMN intro_text_original TO word_intro_hindi;
ALTER TABLE word_introduction RENAME COLUMN intro_text_translation TO word_intro_english;


