create table friend_request
(
    id          bigint auto_increment primary key,
    send_date   datetime not null,
    sender_id   bigint   not null,
    receiver_id bigint   not null,
    foreign key (sender_id) references users (id),
    foreign key (receiver_id) references users (id)
);