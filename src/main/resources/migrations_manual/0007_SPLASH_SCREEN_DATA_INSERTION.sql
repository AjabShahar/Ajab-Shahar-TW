
INSERT INTO SPLASH_SCREEN (
    URL,
	IMAGE_URL,
	FORMAT
) 
VALUES
( 'O-WVDBpBdRY','http://localhost/contentAsset/raw-data/ddfcb080-b11c-46c9-bbf2-c39e81a7cd7a/file?byInode=true', 'audio'),
( 'O-WVDBpBdRY','','video');

--rollback delete from SPLASH_SCREEN where URL='O-WVDBpBdRY';