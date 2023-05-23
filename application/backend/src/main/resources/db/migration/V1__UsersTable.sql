create table users
(
    id       bigint auto_increment primary key,
    username varchar(255) unique not null,
    email    varchar(255) unique not null,
    password varchar(255)        not null,
    role     varchar(255)        not null
);