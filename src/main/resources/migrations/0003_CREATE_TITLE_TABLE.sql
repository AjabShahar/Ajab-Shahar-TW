--liquibase formatted sql

--changeset PADMA:32
CREATE TABLE title
(
  id serial NOT NULL,
  original_title character varying(200),
  english_translation character varying(200),
  english_transliteration character varying(200),
  category_id integer,
  CONSTRAINT umbrella_title_pkey PRIMARY KEY (id),
  CONSTRAINT title_category_id_fkey FOREIGN KEY (category_id)
      REFERENCES category (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

--rollback DROP TABLE title;