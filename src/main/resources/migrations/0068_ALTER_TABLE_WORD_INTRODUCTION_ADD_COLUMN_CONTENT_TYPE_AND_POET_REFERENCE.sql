--liquibase formatted sql 

--changeset chanakya_jaideep:68

ALTER TABLE word_introduction ADD COLUMN content_type varchar(200);
ALTER TABLE word_introduction ALTER COLUMN content_type;
ALTER TABLE word_introduction ADD COLUMN poet_id integer;
ALTER TABLE word_introduction ADD CONSTRAINT poet_id_fkey FOREIGN KEY (poet_id) REFERENCES person (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;
