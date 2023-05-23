create table user_friendship
(
    id          bigint auto_increment primary key,
    accept_date datetime not null,
    user_id     bigint   not null,
    friend_id   bigint   not null,
    foreign key (user_id) references users (id),
    foreign key (friend_id) references users (id)
);