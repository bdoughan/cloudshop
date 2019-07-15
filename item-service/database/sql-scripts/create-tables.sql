create table items (
    id bigint not null auto_increment,
    name tinytext not null,
    description text,
    price decimal(11, 2) not null,
    primary key (id)
);
