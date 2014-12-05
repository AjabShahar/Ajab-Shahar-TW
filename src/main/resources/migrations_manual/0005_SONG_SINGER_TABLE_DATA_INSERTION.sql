--liquibase formatted sql

--changeset PADMA:12
INSERT INTO SONG_SINGER(
    SONG_ID ,
    SINGER_ID
)
VALUES
(1,1),
(2,3),
(3,5);

--rollback delete from SONG_SINGER where SONG_ID='1';
--rollback delete from SONG_SINGER where SONG_ID='2';
--rollback delete from SONG_SINGER where SONG_ID='3';