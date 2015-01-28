--liquibase formatted sql

--changeset Jaideep:21

INSERT INTO category(name, category_type) select distinct category, 'person' from person;
