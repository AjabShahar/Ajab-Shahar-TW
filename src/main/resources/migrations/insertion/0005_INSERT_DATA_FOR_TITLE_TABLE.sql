--liquibase formatted sql

--changeset JAIDEEP:5
INSERT INTO TITLE (
    ORIGINAL,
    ENGLISH_TRANSLATION,
    ENGLISH_TRANSLITERATION
)
VALUES
	('संत कबीर','Saint Kabir', 'Sant Kabir')
;

--delete from TITLE where ENGLISH_TRANSLATION='Saint Kabir';