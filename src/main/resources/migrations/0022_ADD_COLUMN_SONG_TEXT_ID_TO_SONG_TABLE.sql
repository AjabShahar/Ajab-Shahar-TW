--liquibase formatted sql

--changeset Padma:22
ALTER TABLE song ADD COLUMN song_text_id integer;
ALTER TABLE song ADD CONSTRAINT song_song_text_id_fkey FOREIGN KEY (song_text_id)
      REFERENCES song_text (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;


--rollback: drop column song_text_id;