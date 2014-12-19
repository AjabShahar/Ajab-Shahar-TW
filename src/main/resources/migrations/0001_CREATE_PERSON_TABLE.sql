
--liquibase formatted sql

--changeset Soumya:1
CREATE TABLE person
(
  id serial NOT NULL,
  first_name character varying(130) NOT NULL,
  middle_name character varying(130),
  last_name character varying(130),
  category character varying(200) NOT NULL,
  CONSTRAINT person_pkey PRIMARY KEY (id)
)


--rollback DROP TABLE person;