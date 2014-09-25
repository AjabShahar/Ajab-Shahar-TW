--liquibase formatted sql

--changeset JAIDEEP:2
CREATE TABLE WORD (
    TAG_ID INT FOREIGN KEY,
    ID ID SERIAL,
    WORD VARCHAR(100) NOT NULL
);
--rollback drop table WORD;