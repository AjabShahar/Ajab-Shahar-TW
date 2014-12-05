--liquibase formatted sql

--changeset PADMA:13
INSERT INTO SONG_POET(
    SONG_ID,
    POET_ID
)
VALUES
(1,2),
(2,4),
(3,2);

--rollback delete from SONG_POET where SONG_ID='1';
--rollback delete from SONG_POET where SONG_ID='2';
--rollback delete from SONG_POET where SONG_ID='3';