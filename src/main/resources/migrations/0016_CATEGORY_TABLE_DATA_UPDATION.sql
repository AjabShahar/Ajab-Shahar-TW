--liquibase formatted sql

--changeset JAIDEEp:16
UPDATE CATEGORY SET CATEGORY_TYPE='song' where name = 'Songs';
UPDATE CATEGORY SET CATEGORY_TYPE='song' where name = 'Song & Reflection';