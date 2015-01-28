--liquibase formatted sql

--changeset Padma:47
CREATE TABLE reflection
(
    id serial NOT NULL,
    title CHARACTER VARYING(200),
    speaker_id INTEGER,
    youtube_video_id CHARACTER VARYING(300),
    sound_cloud_track_id CHARACTER VARYING(400),
    CONSTRAINT reflection_pkey PRIMARY KEY(id),
    CONSTRAINT speaker_id_fkey FOREIGN KEY(speaker_id)
      REFERENCES person(id) MATCH SIMPLE
);

