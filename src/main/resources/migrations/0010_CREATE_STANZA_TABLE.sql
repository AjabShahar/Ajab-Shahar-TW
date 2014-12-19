--liquibase formatted sql

--changeset Jaideep:10
CREATE TABLE stanza
(
  id serial not null,
  original_text character varying(500),
  english_translation_text character varying(500),
  english_transliteration_text character varying(500),
  CONSTRAINT stanza_pkey PRIMARY KEY (id)
);

--rollback drop table SONG_POET;