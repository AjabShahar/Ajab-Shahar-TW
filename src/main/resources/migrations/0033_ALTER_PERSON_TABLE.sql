--liquibase formatted sql

--changeset Padma:33
ALTER TABLE person ADD COLUMN first_name_in_hindi character varying(200);
ALTER TABLE person ADD COLUMN middle_name_in_hindi character varying(200);
ALTER TABLE person ADD COLUMN last_name_in_hindi character varying(200);