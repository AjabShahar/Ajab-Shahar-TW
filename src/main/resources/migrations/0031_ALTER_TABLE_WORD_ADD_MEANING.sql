--liquibase formatted sql

--changeset sswaroop:31
ALTER TABLE Word ADD meaning character varying(500);
