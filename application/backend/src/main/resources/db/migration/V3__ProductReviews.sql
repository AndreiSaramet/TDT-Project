create table product_review
(
    id          bigint auto_increment primary key,
    text        varchar(1000) not null,
    rating      int           not null,
    post_date   datetime      not null,
    product_id  bigint        not null,
    reviewer_id bigint        not null,
    foreign key (product_id) references product (id),
    foreign key (reviewer_id) references users (id)
);