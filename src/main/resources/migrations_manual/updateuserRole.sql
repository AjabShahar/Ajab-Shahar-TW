
--liquibase formatted sql

--context test

update users set role='admin' where username='admin';