--liquibase formatted sql

--changeset sswaroop:50
CREATE TABLE related_words
(
  id serial NOT NULL,
  word_id integer,
  related_word_id integer,
  CONSTRAINT related_words_pkey PRIMARY KEY (id),
  CONSTRAINT word_related_words_word_id_fkey FOREIGN KEY (word_id)
      REFERENCES word (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE SET NULL,
  CONSTRAINT word_related_words_related_word_id_fkey FOREIGN KEY (related_word_id)
      REFERENCES word (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE SET NULL
);