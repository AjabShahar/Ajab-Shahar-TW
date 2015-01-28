--liquibase formatted sql

--changeset Padma:32
CREATE TABLE song_text_opening_couplet
(
  id serial NOT NULL,
  song_text_id integer,
  opening_couplet_id integer,
  CONSTRAINT song_text_opening_couplet_id_pkey PRIMARY KEY (id),
  CONSTRAINT song_text_id_fkey FOREIGN KEY (song_text_id)
        REFERENCES song_text (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT song_text_opening_couplet_id_fkey FOREIGN KEY (opening_couplet_id)
        REFERENCES opening_couplet (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);

