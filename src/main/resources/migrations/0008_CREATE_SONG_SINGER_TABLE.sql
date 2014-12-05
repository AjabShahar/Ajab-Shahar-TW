--liquibase formatted sql

--changeset PADMA:7
CREATE TABLE song_singer
(
  id serial NOT NULL,
  singer_id integer,
  song_id integer,
  CONSTRAINT song_singer_pkey PRIMARY KEY (id),
  CONSTRAINT song_singer_singer_id_fkey FOREIGN KEY (singer_id)
      REFERENCES person (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT song_singer_song_id_fkey FOREIGN KEY (song_id)
      REFERENCES song (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
;

--rollback drop table SONG;