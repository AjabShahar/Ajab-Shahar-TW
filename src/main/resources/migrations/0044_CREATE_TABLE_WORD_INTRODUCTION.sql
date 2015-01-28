--liquibase formatted sql

--changeset Jaideep:44

CREATE TABLE word_introduction
(
	id serial not null,
	word_id integer,
	intro_text_original character varying(600),
	intro_text_translation character varying(600),
	intro_text_transliteration character varying(600),
	CONSTRAINT word_id_fkey FOREIGN KEY (word_id) 
		REFERENCES word(id) MATCH SIMPLE
);
