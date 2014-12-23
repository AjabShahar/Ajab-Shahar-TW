--liquibase formatted sql

--changeset Jaideep:15

ALTER TABLE lyrics ADD
	sequenceOrder integer;

--rollback ALTER TABLE lyrics DROP sequenceOrder;