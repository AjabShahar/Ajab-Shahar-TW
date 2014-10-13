--liquibase formatted sql

--changeset PADMA:10
INSERT INTO SONG(
    SHOW_ON_LANDING_PAGE,
    CATEGORY,
    DURATION,
    YOUTUBE_VIDEO_ID,
    THUMBNAIL_URL,
    ORIGINAL_TITLE,
    ENGLISH_TRANSLATION,
    ENGLISH_TRANSLITERATION
)
VALUES
(TRUE,'SONG','5:45','tNh2kjmSzPw','','किछु दिन मोने मोने','For a few days,O Heart','Kichhu din mone mone'),
(TRUE,'SONG','5:10','7Gg0vSOZhJQ','','भजन रो गुड़क रहयो गाड़ो','The Cart of Meditation is Tottering','Bhajan Ro Guḍak Rahyo Gaaḍo'),
(TRUE,'SONG','7:05','J4IU5tDlD_s','','हिए काया में','In This Body','Hiye Kaaya Mein');