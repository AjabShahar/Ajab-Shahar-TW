--liquibase formatted sql

--changeset PADMA:10
INSERT INTO SONG_SINGER(
    SONG_ID ,
    SINGER_ID
)
VALUES
(1,1),
(2,3),
(3,4);