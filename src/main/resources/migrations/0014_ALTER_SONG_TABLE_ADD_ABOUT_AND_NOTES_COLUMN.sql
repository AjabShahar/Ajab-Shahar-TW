	
--liquibase formatted sql

--changeset Jaideep:14

ALTER TABLE SONG ADD
	about character varying(500),
	ADD notes character varying(500);

--rollback ALTER TABLE song DROP about;
--rollback ALTER TABLE song DROP notes;