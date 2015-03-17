--liquibase formatted sql 

--changeset CLAXTON:61

CREATE TABLE GATHERING(
	id serial NOT NULL PRIMARY KEY,
	original_text TEXT,
	english_text TEXT
)