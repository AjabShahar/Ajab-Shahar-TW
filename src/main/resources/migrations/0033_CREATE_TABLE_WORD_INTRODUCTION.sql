--liquibase formatted sql

--changeset Jaideep:19

CREATE TABLE word_introduction
(
	id serial not null,
	word_id integer,
	introduction_text integer,
	CONSTRAINT word_id_fkey FOREIGN KEY (word_id) 
		REFERENCES word(id) MATCH SIMPLE
);
