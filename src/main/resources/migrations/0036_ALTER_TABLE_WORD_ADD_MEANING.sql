--liquibase formatted sql

--changeset sswaroop:36
ALTER TABLE Word ADD meaning character varying(500);
