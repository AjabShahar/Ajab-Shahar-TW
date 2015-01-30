--liquibase formatted sql 

--changeset JAIDEEP:50

CREATE TABLE GENRE(
	id serial NOT NULL,
	ORIGINAL_TEXT varchar(60),
	ENGLISH_TEXT varchar(60)
)