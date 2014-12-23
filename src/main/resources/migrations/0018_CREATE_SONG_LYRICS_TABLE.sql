--liquibase formatted sql

--changeset Padma:18

CREATE TABLE song_lyrics
(
  id serial NOT NULL,
  song_id integer,
  lyrics_id integer,
  CONSTRAINT song_lyrics_pkey PRIMARY KEY (id),
  CONSTRAINT song_lyrics_song_id_fkey FOREIGN KEY (song_id)
      REFERENCES song (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT song_lyrics_lyrics_id_fkey FOREIGN KEY (lyrics_id)
      REFERENCES lyrics (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
--rollback DROP TABLE SONG_LYRICS;