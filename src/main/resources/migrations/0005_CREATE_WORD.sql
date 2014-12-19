--liquibase formatted sql

--changeset PADMA:5
CREATE TABLE word
(
  id serial NOT NULL,
  word_or_phrase character varying(100) NOT NULL,
  synonym character varying(100) NOT NULL,
  show_on_landing_page boolean NOT NULL,
  thumbnail_url character varying(100),
  category_id integer,
  CONSTRAINT word_pkey PRIMARY KEY (id),
  CONSTRAINT word_category_id_fkey FOREIGN KEY (category_id)
      REFERENCES category (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

--rollback drop table WORD;