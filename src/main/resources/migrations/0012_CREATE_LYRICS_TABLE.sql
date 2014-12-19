--liquibase formatted sql

--changeset Padma:12
CREATE TABLE lyrics
(
  id serial NOT NULL,
  chorus character varying(300),
  song_id integer,
  couplet_id integer,
  stanza_id integer,
  CONSTRAINT song_id_fkey FOREIGN KEY (song_id)
        REFERENCES song (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT couplet_id_fkey FOREIGN KEY (couplet_id)
          REFERENCES couplet (id) MATCH SIMPLE
          ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT stanza_id_fkey FOREIGN KEY (stanza_id)
          REFERENCES stanza (id) MATCH SIMPLE
          ON UPDATE NO ACTION ON DELETE NO ACTION
);