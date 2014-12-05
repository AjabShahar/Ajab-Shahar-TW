--liquibase formatted sql

--changeset PADMA:6
CREATE TABLE splash_screen
(
  id serial NOT NULL,
  url character varying(300),
  image_url character varying(300),
  format character varying(100) NOT NULL,
  CONSTRAINT splash_screen_pkey PRIMARY KEY (id)
)
;

--rollback drop table SPLASH_SCREEN;