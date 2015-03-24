--liquibase formatted sql 

--changeset JAIDEEP_CHANAKYA:64

ALTER TABLE SONG ADD COLUMN gathering_id INT;
ALTER TABLE SONG add CONSTRAINT gathering_id_fkey FOREIGN KEY(gathering_id) REFERENCES gathering(id) MATCH SIMPLE;