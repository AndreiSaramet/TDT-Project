create table user_review
(
    id          bigint auto_increment primary key,
    text        varchar(1000) not null,
    rating      int           not null,
    post_date   datetime      not null,
    poster_id   bigint        not null,
    receiver_id bigint        not null,
    foreign key (poster_id) references users (id),
    foreign key (receiver_id) references users (id)
);