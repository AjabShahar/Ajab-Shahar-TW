--liquibase formatted sql

--changeset Jaideep:8
CREATE TABLE chorus
(
  original_text character varying(300),
  english_translation_text character varying(300),
  english_transliteration_text character varying(300)
)
;

--rollback drop table SONG_POET;