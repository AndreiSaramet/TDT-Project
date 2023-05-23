create table user_profile
(
    id              bigint primary key,
    bio             varchar(255) not null,
    profile_picture longblob     not null,
    foreign key (id) references users (id) on delete cascade on update cascade
);