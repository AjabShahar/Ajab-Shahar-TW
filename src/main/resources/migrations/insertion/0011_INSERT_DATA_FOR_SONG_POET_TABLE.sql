--liquibase formatted sql

--changeset PADMA:10
INSERT INTO SONG_POET(
    SONG_ID,
    POET_ID
)
VALUES
(1,2),
(2,4),
(3,2);