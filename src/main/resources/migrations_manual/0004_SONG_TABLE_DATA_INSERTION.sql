--liquibase formatted sql

--changeset PADMA:11
INSERT INTO SONG(
    SHOW_ON_LANDING_PAGE,
    DURATION,
    YOUTUBE_VIDEO_ID,
    SONG_TITLE_ID,
    UMBRELLA_TITLE_ID,
    SONG_CATEGORY,
    MEDIA_CATEGORY,
    SOUNDCLOUD_TRACK_ID,
    IS_AUTHORING_COMPLETE,
    THUMBNAIL_URL
)
VALUES
(TRUE,'5:45','tNh2kjmSzPw',1,1,1,4,'173752179',TRUE,'http://3.bp.blogspot.com/-kwpgiMcXc24/TcOcowo6mTI/AAAAAAAAA9w/uNt6ZsJadDg/s1600/parvathy_baul03.jpg'),
(TRUE,'5:10','7Gg0vSOZhJQ',2,2,1,3,'173752179',TRUE,'http://i.ytimg.com/vi/J4IU5tDlD_s/mqdefault.jpg'),
(TRUE,'7:05','J4IU5tDlD_s',3,3,1,4,'173752179',TRUE,'http://mountainshepherds.com/wp-content/gallery/archival/gauradevi.jpg');