--liquibase formatted sql

--changeset PADMA:9
CREATE TABLE song_poet
(
  id serial NOT NULL,
  poet_id integer,
  song_id integer,
  CONSTRAINT song_poet_pkey PRIMARY KEY (id),
  CONSTRAINT song_poet_poet_id_fkey FOREIGN KEY (poet_id)
      REFERENCES person (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT song_poet_song_id_fkey FOREIGN KEY (song_id)
      REFERENCES song (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
;

--rollback drop table SONG_POET;