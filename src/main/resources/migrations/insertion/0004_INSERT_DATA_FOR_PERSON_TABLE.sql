--liquibase formatted sql

--changeset JAIDEEP:4
INSERT INTO PERSON (
    FIRST_NAME,
    MIDDLE_NAME,
    LAST_NAME
)
VALUES
	('Sonu', '', 'Nigam'),
	('Rahul', 'Dev', 'Burman');

--delete from PERSON where FIRST_NAME='Sonu';
--delete from PERSON where FIRST_NAME='Rahul';