--liquibase formatted sql 

--changeset Indraneel:59
ALTER TABLE users ADD COLUMN role  VARCHAR(20);
