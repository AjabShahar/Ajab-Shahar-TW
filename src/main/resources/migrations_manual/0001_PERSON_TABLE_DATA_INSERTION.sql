
INSERT INTO PERSON(
 FIRST_NAME,
 MIDDLE_NAME,
 LAST_NAME,
 CATEGORY
)
VALUES
('Parvathy','','Baul','Singer'),
('Roshik','','','Poet'),
('Gavra Devi','','','Singer'),
('Fakru','','','Poet'),
('Mooralala','','Marwada','Singer'),
('Kabir','','','Poet'),
('Unknown','','','Unknown');

--rollback delete from PERSON where FIRST_NAME='PARVATHY';
--rollback delete from PERSON where FIRST_NAME='ROSHIK';
--rollback delete from PERSON where FIRST_NAME='Gavra Devi';
--rollback delete from PERSON where FIRST_NAME='Fakru';
--rollback delete from PERSON where FIRST_NAME='Mooralala';
--rollback delete from PERSON where FIRST_NAME='Kabir';