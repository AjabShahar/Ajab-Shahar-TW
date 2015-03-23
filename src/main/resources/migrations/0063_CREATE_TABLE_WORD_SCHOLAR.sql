--liquibase formatted sql 

--changeset JAIDEEP_CHANAKYA:63

create table word_scholar (
  id serial not null,
  word_id integer,
  scholar_id integer,
  CONSTRAINT word_scholar_pkey PRIMARY KEY (id),
  CONSTRAINT word_id_fkey FOREIGN KEY (word_id)
   REFERENCES word(id) MATCH SIMPLE
   ON UPDATE NO ACTION ON DELETE SET NULL,
  CONSTRAINT scholar_id_fkey FOREIGN KEY (scholar_id)
    REFERENCES person(id) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE SET NULL
);