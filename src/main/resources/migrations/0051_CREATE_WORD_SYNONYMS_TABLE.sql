--liquibase formatted sql

--changeset sswaroop:51
CREATE TABLE word_synonyms
(
  id serial NOT NULL,
  word_id integer,
  synonym_word_id integer,
  CONSTRAINT word_synonyms_pkey PRIMARY KEY (id),
  CONSTRAINT word_word_synonyms_word_id_fkey FOREIGN KEY (word_id)
      REFERENCES word (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE SET NULL,
  CONSTRAINT word_word_synonyms_synonym_word_id_fkey FOREIGN KEY (synonym_word_id)
      REFERENCES word (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE SET NULL
);