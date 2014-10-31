--liquibase formatted sql

--changeset JAIDEEp:17
INSERT INTO CATEGORY(NAME, CATEGORY_TYPE) VALUES('video', 'media'), ('audio', 'media'),('Couplet','couplet');