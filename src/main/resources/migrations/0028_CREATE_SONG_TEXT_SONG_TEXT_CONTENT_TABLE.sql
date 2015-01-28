--liquibase formatted sql

--changeset Padma:28
CREATE TABLE song_text_song_text_content
(
  id serial NOT NULL,
  song_text_id integer,
  song_text_content_id integer,
  CONSTRAINT song_text_content_pkey PRIMARY KEY (id),
  CONSTRAINT song_text_id_fkey FOREIGN KEY (song_text_id)
        REFERENCES song_text (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT song_text_content_id_fkey FOREIGN KEY (song_text_content_id)
        REFERENCES song_text_content (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);

--rollback: drop table song_text_song_text_content;