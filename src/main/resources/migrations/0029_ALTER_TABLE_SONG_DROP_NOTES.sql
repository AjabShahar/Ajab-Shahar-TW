--liquibase formatted sql

--changeset Jaideep:29
ALTER TABLE song DROP COLUMN notes;
