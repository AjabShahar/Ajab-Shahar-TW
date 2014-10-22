--liquibase formatted sql

--changeset PADMA:11
INSERT INTO SONG(
    SHOW_ON_LANDING_PAGE,
    DURATION,
    YOUTUBE_VIDEO_ID,
    THUMBNAIL_URL,
    ORIGINAL_TITLE,
    ENGLISH_TRANSLATION,
    ENGLISH_TRANSLITERATION,
    CATEGORY_ID
)
VALUES
(TRUE,'5:45','tNh2kjmSzPw','','किछु दिन मोने मोने','For a few days,O Heart','Kichhu din mone mone',1),
(TRUE,'5:10','7Gg0vSOZhJQ','','भजन रो गुड़क रहयो गाड़ो','The Cart of Meditation is Tottering','Bhajan Ro Guḍak Rahyo Gaaḍo',1),
(TRUE,'7:05','J4IU5tDlD_s','','हिए काया में','In This Body','Hiye Kaaya Mein',1);

--rollback delete from SONG where ENGLISH_TRANSLITERATION='Kichhu din mone mone';
--rollback delete from SONG where ENGLISH_TRANSLITERATION='Bhajan Ro Guḍak Rahyo Gaaḍo';
--rollback delete from SONG where ENGLISH_TRANSLITERATION='Hiye Kaaya Mein';