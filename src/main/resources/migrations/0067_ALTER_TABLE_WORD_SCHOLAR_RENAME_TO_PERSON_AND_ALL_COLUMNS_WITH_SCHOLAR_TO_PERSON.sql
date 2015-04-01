--liquibase formatted sql 

--changeset CHANAKYA:67

ALTER TABLE word_scholar RENAME COLUMN scholar_id TO person_id;
ALTER TABLE word_scholar RENAME CONSTRAINT word_scholar_pkey TO word_person_pkey;
ALTER TABLE word_scholar RENAME CONSTRAINT scholar_id_fkey TO person_id_fkey;
ALTER TABLE word_scholar RENAME TO word_person;
