
--liquibase formatted sql

--changeset Jaideep:4
CREATE TABLE song
(
  id serial NOT NULL,
  show_on_landing_page boolean,
  duration character varying(200),
  youtube_video_id character varying(20),
  thumbnail_url character varying(200),
  song_category integer,
  media_category integer,
  soundcloud_track_id character varying(40),
  is_authoring_complete boolean,
  umbrella_title_id integer,
  song_title_id integer,
  CONSTRAINT song_pkey PRIMARY KEY (id),
  CONSTRAINT song_category_id_fkey FOREIGN KEY (song_category)
      REFERENCES category (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT song_media_category_fkey FOREIGN KEY (media_category)
      REFERENCES category (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT song_song_title_id_fkey FOREIGN KEY (song_title_id)
      REFERENCES title (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT song_umbrella_title_id_fkey FOREIGN KEY (umbrella_title_id)
      REFERENCES title (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

--rollback DROP TABLE song;