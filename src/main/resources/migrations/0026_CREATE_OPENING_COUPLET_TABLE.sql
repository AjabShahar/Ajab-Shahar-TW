--liquibase formatted sql

--changeset Padma:26
CREATE TABLE opening_couplet(
   id serial not null,
   original_text character varying(500),
   english_translation_text character varying(500),
   english_transliteration_text character varying(500),
   content_type character varying(100),
   sequence_number integer,
   poet_id integer,
   CONSTRAINT opening_couplet_pkey PRIMARY KEY (id),
   CONSTRAINT opening_couplet_id_fkey FOREIGN KEY (poet_id)
       REFERENCES person (id) MATCH SIMPLE
       ON UPDATE NO ACTION ON DELETE NO ACTION
);