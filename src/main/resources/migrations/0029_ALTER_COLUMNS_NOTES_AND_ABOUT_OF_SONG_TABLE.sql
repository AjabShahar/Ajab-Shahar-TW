--liquibase formatted sql

--changeset Jaideep:29

ALTER TABLE SONG ALTER COLUMN about TYPE character varying(1500);

ALTER TABLE SONG ALTER COLUMN notes TYPE character varying(1500);