--liquibase formatted sql 

--changeset sswaroop:66

ALTER TABLE person ADD COLUMN primary_occupation integer;
ALTER TABLE person ADD CONSTRAINT primary_occupation_fkey FOREIGN KEY (primary_occupation)
 REFERENCES category (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;