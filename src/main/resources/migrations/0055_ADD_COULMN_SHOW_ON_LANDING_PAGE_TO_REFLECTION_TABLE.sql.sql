--liquibase formatted sql 

--changeset Padma:55

ALTER TABLE reflection add column show_on_landing_page boolean;