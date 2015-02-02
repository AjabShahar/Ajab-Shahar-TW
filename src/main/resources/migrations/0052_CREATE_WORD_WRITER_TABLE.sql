--liquibase formatted sql 

--changeset Padma:52
create table word_writer (
  id serial not null,
  word_id integer,
  writer_id integer,
  CONSTRAINT word_writer_pkey PRIMARY KEY (id),
  CONSTRAINT word_id_fkey FOREIGN KEY (word_id)
   REFERENCES word(id) MATCH SIMPLE
   ON UPDATE NO ACTION ON DELETE SET NULL,
  CONSTRAINT writer_id_fkey FOREIGN KEY (writer_id)
    REFERENCES person(id) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE SET NULL
);