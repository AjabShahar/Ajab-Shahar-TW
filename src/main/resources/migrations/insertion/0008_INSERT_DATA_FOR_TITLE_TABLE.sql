--liquibase formatted sql

--changeset PADMA:8
INSERT INTO TITLE(
 ORIGINAL,
 ENGLISH_TRANSLATION,
 ENGLISH_TRANSLITERATION
)
VALUES
('किछु दिन मोने मोने','For a few days,O Heart','Kichhu din mone mone'),
('भजन रो गुड़क रहयो गाड़ो','The Cart of Meditation is Tottering','Bhajan Ro Guḍak Rahyo Gaaḍo'),
('हिए काया में','In This Body','Hiye Kaaya Mein');

--delete from TITLE where ENGLISH_TRANSLITERATION='O-Kichhu din mone mone';
--delete from TITLE where ENGLISH_TRANSLITERATION='Bhajan Ro Guḍak Rahyo Gaaḍo';
--delete from TITLE where ENGLISH_TRANSLITERATION='Hiye Kaaya Mein';