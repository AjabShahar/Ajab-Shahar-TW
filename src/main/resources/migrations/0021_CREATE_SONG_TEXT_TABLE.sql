--liquibase formatted sql

--changeset Padma:21
CREATE TABLE song_text
(
  id serial NOT NULL,
  refrain_original varchar (500),
  refrain_english_translation varchar(500),
  refrain_english_transliteration varchar(500),
  CONSTRAINT song_text_pkey PRIMARY KEY (id)
);

--rollback: drop table song_text;