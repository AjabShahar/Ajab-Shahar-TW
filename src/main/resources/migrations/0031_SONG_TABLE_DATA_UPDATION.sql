--liquibase formatted sql

--changeset PADMA:31

UPDATE SONG SET THUMBNAIL_URL = 'http://3.bp.blogspot.com/-kwpgiMcXc24/TcOcowo6mTI/AAAAAAAAA9w/uNt6ZsJadDg/s1600/parvathy_baul03.jpg' WHERE YOUTUBE_VIDEO_ID = 'tNh2kjmSzPw';
UPDATE SONG SET THUMBNAIL_URL = 'http://i.ytimg.com/vi/J4IU5tDlD_s/mqdefault.jpg' WHERE YOUTUBE_VIDEO_ID = 'J4IU5tDlD_s';
UPDATE SONG SET THUMBNAIL_URL = 'http://mountainshepherds.com/wp-content/gallery/archival/gauradevi.jpg' WHERE YOUTUBE_VIDEO_ID = '7Gg0vSOZhJQ';