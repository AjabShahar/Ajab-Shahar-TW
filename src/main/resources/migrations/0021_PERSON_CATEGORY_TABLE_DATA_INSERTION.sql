--liquibase formatted sql

--changeset Jaideep:21

INSERT INTO person_category(person_id, category_id) select p.id, c.id from person p join category c on p.category = c.name and c.category_type = 'person';

