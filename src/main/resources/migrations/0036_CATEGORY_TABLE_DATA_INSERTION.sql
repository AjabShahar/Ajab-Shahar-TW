--liquibase formatted sql

--changeset PADMA:36
INSERT INTO CATEGORY(
    NAME,
    CATEGORY_TYPE
)
VALUES
('Song Title','Song Title'),
('Umbrella Title','Umbrella Title');
