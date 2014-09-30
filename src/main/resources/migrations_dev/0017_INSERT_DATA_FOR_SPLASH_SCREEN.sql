--liquibase formatted sql

--changeset JAIDEEP:17
INSERT INTO SPLASH_SCREEN (
    URL,
	IMAGE_URL,
	FORMAT
) 
VALUES
( 'O-WVDBpBdRY','http://localhost/contentAsset/raw-data/ddfcb080-b11c-46c9-bbf2-c39e81a7cd7a/file?byInode=true', 'audio'),
( 'O-WVDBpBdRY','','video');

--delete from SPLASH_SCREEN;