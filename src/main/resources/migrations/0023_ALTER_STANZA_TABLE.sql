--liquibase formatted sql

--changeset Padma:23
ALTER TABLE stanza RENAME TO song_text_content;
ALTER TABLE song_text_content ADD COLUMN content_type varchar(100);
ALTER TABLE song_text_content ADD COLUMN sequence_number integer;
ALTER TABLE song_text_content ADD COLUMN poet_id integer;
ALTER TABLE song_text_content ADD CONSTRAINT song_song_text_id_fkey FOREIGN KEY (poet_id)
    REFERENCES person (id) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE NO ACTION;

--rollback: drop table couple_song_text;