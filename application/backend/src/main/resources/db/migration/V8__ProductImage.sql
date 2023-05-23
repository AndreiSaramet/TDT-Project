create table product_image
(
    id    bigint primary key,
    image longblob not null,
    foreign key (id) references product (id) on delete cascade on update cascade
);