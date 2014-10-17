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

--rollback delete from PERSON where FIRST_NAME='PARVATHY';
--rollback delete from PERSON where FIRST_NAME='ROSHIK';
--rollback delete from PERSON where FIRST_NAME='Gavra Devi';
--rollback delete from PERSON where FIRST_NAME='Fakru';
--rollback delete from PERSON where FIRST_NAME='Mooralala';
--rollback delete from PERSON where FIRST_NAME='Kabir';