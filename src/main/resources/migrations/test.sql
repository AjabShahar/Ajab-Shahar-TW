--liquibase formatted sql

--changeset authorName:1
create table test1 (
    id int primary key,
    name varchar(255)
);
--rollback drop table test1;

--changeset authorName:2 dbms:postgresql
create sequence seq_test;