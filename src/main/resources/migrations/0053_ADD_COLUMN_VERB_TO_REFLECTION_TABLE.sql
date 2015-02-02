--liquibase formatted sql 

--changeset Padma:53

ALTER TABLE reflection add column verb character varying(600);