--liquibase formatted sql 

--changeset padma:58

create table users(
  id serial not null,
  username varchar(30) not null,
  password text not null,
  constraint p_key primary key (id)
);