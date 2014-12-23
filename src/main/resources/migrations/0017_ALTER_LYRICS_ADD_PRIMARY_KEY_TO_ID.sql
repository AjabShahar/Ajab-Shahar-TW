--liquibase formatted sql

--changeset Padma:17

ALTER TABLE lyrics add CONSTRAINT  lyrics_pkey PRIMARY KEY (id);

--rollback ALTER TABLE lyrics DROP COLUMN id;
