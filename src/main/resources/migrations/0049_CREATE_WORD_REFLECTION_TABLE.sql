--liquibase formatted sql

--changeset PADMA:49
CREATE TABLE word_reflection
(
  id serial NOT NULL,
  word_id integer,
  reflection_id integer,
  CONSTRAINT word_reflection_pkey PRIMARY KEY (id),
  CONSTRAINT word_reflection_word_id_fkey FOREIGN KEY (word_id)
      REFERENCES word (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE SET NULL,
  CONSTRAINT word_reflection_reflection_id_fkey FOREIGN KEY (reflection_id)
      REFERENCES reflection (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE SET NULL
);