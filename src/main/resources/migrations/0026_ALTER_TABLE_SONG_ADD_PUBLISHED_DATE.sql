--liquibase formatted sql

--changeset Jaideep:26

ALTER TABLE SONG ADD
	published_date TIMESTAMP;