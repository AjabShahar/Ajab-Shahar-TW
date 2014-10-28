--liquibase formatted sql

--changeset JAIDEEp:17
UPDATE CATEGORY SET NAME='video and audio' where NAME = 'video';
UPDATE CATEGORY SET NAME='audio only' where name = 'audio';