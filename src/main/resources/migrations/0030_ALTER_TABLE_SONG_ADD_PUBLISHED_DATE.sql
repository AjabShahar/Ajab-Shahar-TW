--liquibase formatted sql

--changeset Jaideep:30

ALTER TABLE SONG ADD
	published_date TIMESTAMP;