	
--liquibase formatted sql

--changeset Jaideep:13

ALTER TABLE SONG ADD
download_url character varying(200);

--rollback ALTER TABLE song DROP download_url;