--liquibase formatted sql 

--changeset padma:54

ALTER TABLE reflection add column is_authoring_complete boolean;