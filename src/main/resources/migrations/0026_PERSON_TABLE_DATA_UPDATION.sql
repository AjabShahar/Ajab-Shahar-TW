--liquibase formatted sql

--changeset PADMA:26
INSERT INTO PERSON(
  FIRST_NAME,
  MIDDLE_NAME,
  LAST_NAME,
  CATEGORY
)
VALUES(
'Unknown','','','Unknown');
