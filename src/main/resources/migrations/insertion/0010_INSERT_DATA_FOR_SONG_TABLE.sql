--liquibase formatted sql

--changeset PADMA:10
INSERT INTO SONG(
    TITLE_ID ,
    SINGER_ID ,
    POET_ID ,
    SHOW_ON_LANDING_PAGE,
    CATEGORY,
    DURATION,
    YOUTUBE_VIDEO_ID
)
VALUES
(1,1,2,TRUE,'SONG','5:45','tNh2kjmSzPw'),
(2,3,4,TRUE,'SONG','5:10','7Gg0vSOZhJQ'),
(3,4,2,TRUE,'SONG','7:05','J4IU5tDlD_s');

--delete from TITLE where ID=1;
--delete from TITLE where ID=2;
--delete from TITLE where ID=3;

--delete from PERSON where ID=1;
--delete from PERSON where ID=2;
--delete from PERSON where ID=3;
--delete from PERSON where ID=4;

--delete from SONG where TITLE_ID=1;
--delete from SONG where TITLE_ID=2;
--delete from SONG where TITLE_ID=3;