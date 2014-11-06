--liquibase formatted sql

--changeset PADMA:38
INSERT INTO TITLE(
   ORIGINAL_TITLE,
   ENGLISH_TRANSLATION,
   ENGLISH_TRANSLITERATION,
   CATEGORY_ID
)
VALUES
('किछु दिन मोने मोने','For a few days,O Heart','Kichhu din mone mone',8),
('भजन रो गुड़क रहयो गाड़ो','The Cart of Meditation is Tottering','Bhajan Ro Guḍak Rahyo Gaaḍo',8),
('हिए काया में','In This Body','Hiye Kaaya Mein',8);