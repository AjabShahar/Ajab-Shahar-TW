--liquibase formatted sql

--changeset PADMA:11
INSERT INTO CATEGORY(
  NAME, CATEGORY_TYPE
)
VALUES
('Songs','song'),
('Song & Reflection','song'),
('video and audio','media'),
('audio only','media'),
('Couplet','couplet'),
('Word intro','word'),
('Song Title','Song Title'),
('Umbrella Title','Umbrella Title');