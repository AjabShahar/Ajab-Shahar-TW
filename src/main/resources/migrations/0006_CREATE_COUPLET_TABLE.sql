--liquibase formatted sql

--changeset PADMA:5
CREATE TABLE couplet
(
  id serial NOT NULL,
  original_title character varying(200) NOT NULL,
  english_translation character varying(200) NOT NULL,
  english_transliteration character varying(200) NOT NULL,
  show_on_landing_page boolean NOT NULL,
  category_id integer,
  poet_id integer,
  thumbnail_url character varying(200),
  original_text character varying(500),
  english_translation_text character varying(500),
  english_transliteration_text character varying(500),
  CONSTRAINT couplet_pkey PRIMARY KEY (id),
  CONSTRAINT couplet_category_id_fkey FOREIGN KEY (category_id)
      REFERENCES category (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT couplet_poet_id_fkey FOREIGN KEY (poet_id)
      REFERENCES person (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

--rollback drop table COUPLET;