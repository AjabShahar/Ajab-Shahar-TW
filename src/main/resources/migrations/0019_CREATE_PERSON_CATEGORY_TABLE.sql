--liquibase formatted sql

--changeset Jaideep:19

CREATE TABLE person_category
(
	id serial not null,
	person_id integer,
	category_id integer,
	CONSTRAINT category_id_fkey FOREIGN KEY (category_id) 
		REFERENCES category(id) MATCH SIMPLE,
	CONSTRAINT person_id_fkey FOREIGN KEY (person_id)
		REFERENCES person(id) MATCH SIMPLE
);
