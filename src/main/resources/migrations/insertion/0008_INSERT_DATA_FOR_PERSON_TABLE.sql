--liquibase formatted sql

--changeset PADMA:9
INSERT INTO PERSON(
 FIRST_NAME,
 MIDDLE_NAME,
 LAST_NAME,
 CATEGORY
)
VALUES
('PARVATHY','','BAUL','SINGER'),
('ROSHIK','','','POET'),
('Gavra Devi','','','SINGER'),
('Fakru','','','POET'),
('Mooralala','','Marwada','SINGER'),
('Kabir','','','POET');

--delete from PERSON where FIRST_NAME='PARVATHY';
--delete from PERSON where FIRST_NAME='ROSHIK';
--delete from PERSON where FIRST_NAME='Gavra Devi';
--delete from PERSON where FIRST_NAME='Fakru';
--delete from PERSON where FIRST_NAME='Mooralala';
--delete from PERSON where FIRST_NAME='Kabir';