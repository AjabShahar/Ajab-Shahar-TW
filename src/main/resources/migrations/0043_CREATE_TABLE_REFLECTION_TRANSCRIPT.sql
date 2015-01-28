--liquibase formatted sql

--changeset Padma:43
CREATE TABLE transcript(
  id serial NOT NULL,
  text character varying(1500),
  reflection_id integer,
  CONSTRAINT reflection_id_fkey FOREIGN KEY (reflection_id)
  		REFERENCES reflection(id) MATCH SIMPLE
);