
--liquibase formatted sql

--changeset Soumya:2
CREATE TABLE category
(
  id serial NOT NULL,
  name character varying(100) NOT NULL,
  category_type character varying(150),
  CONSTRAINT category_pkey PRIMARY KEY (id)
)


--rollback DROP TABLE category;