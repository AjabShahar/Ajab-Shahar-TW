--liquibase formatted sql 

--changeset Indraneel:62
alter table song ALTER COLUMN soundcloud_track_id TYPE varchar(255);

