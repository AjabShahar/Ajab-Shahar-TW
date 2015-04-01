--liquibase formatted sql 

--changeset JAIDEEP:68

update word_introduction set content_type = 'text' where content_type is null;