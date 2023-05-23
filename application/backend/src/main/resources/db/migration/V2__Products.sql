create table product
(
    id          bigint primary key auto_increment,
    name        varchar(255) not null,
    price       float        not null,
    description varchar(255) not null,
    user_id     bigint       not null,
    foreign key (user_id) references users (id)
);