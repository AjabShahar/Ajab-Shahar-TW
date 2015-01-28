--liquibase formatted sql

--changeset Jaideep:34
ALTER TABLE song DROP COLUMN notes;
