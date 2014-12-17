--liquibase formatted sql

--changeset Jaideep:8
CREATE TABLE stanza
(
  original_text character varying(500),
  english_translation_text character varying(500),
  english_transliteration_text character varying(500)
)
;

--rollback drop table SONG_POET;