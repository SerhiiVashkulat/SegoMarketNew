
drop table if exists bucket_seq;
create table bucket_seq
(
    next_val bigint null
);
insert into bucket_seq values ( 1 );

drop table if exists buckets;
create table buckets
(
    id      bigint not null
        primary key,
    user_id bigint null
);
alter table buckets
    add constraint bucket_fk_user
        foreign key (user_id) references users (id);

drop table if exists categories;
create table categories
(
    id    bigint       not null
        primary key,
    title varchar(255) null
);
drop table if exists category_seq;
create table category_seq
(
    next_val bigint null
);
insert into category_seq values ( 1 );

create table flyway_schema_history
(
    installed_rank int                                 not null
        primary key,
    version        varchar(50)                         null,
    description    varchar(200)                        not null,
    type           varchar(20)                         not null,
    script         varchar(1000)                       not null,
    checksum       int                                 null,
    installed_by   varchar(100)                        not null,
    installed_on   timestamp default CURRENT_TIMESTAMP not null,
    execution_time int                                 not null,
    success        tinyint(1)                          not null
);

create index flyway_schema_history_s_idx
    on flyway_schema_history (success);

drop table if exists order_details_seq;
create table order_details_seq
(
    next_val bigint null
);
insert into order_details_seq values ( 1 );

drop table if exists order_seq;
create table order_seq
(
    next_val bigint null
);
insert into order_seq values ( 1 );

drop table if exists product_seq;
create table product_seq
(
    next_val bigint null
);
insert into product_seq values ( 1 );

drop table if exists products;
create table products
(
    id    bigint         not null
        primary key,
    price decimal(19, 2) null,
    title varchar(255)   null
);

drop table if exists buckets_products;
create table buckets_products
(
    bucket_id  bigint not null,
    product_id bigint not null

);
alter table buckets_products
    add constraint buckets_products_fk_products
        foreign key (product_id) references products (id);
alter table buckets_products
    add constraint buckets_products_fk_bucket
        foreign key (bucket_id) references buckets (id);

drop table if exists products_categories;
create table products_categories
(
    product_id  bigint not null,
    category_id bigint not null

);
alter table products_categories
    add constraint products_categories_fk_category
        foreign key (category_id) references categories (id);
alter table products_categories
    add constraint products_categories_fk_products
        foreign key (product_id) references products (id);

drop table if exists user_seq;
create table user_seq
(
    next_val bigint null
);
insert into user_seq values ( 1 );

drop table if exists users;
create table users
(
    id        bigint       not null
        primary key,
    active    bit          not null,
    email     varchar(255) null,
    name      varchar(255) null,
    password  varchar(255) null,
    role      varchar(255) null,
    bucket_id bigint       null

);
alter table users
    add constraint users_fk_bucket
    foreign key (bucket_id) references buckets (id);

drop table if exists orders;
create table orders
(
    id           bigint         not null
        primary key,
    address      varchar(255)   null,
    created      datetime(6)    null,
    order_status varchar(255)   null,
    sum          decimal(19, 2) null,
    updated      datetime(6)    null,
    user_id      bigint         null

);
alter table orders
    add constraint orders_fk_user
        foreign key (user_id) references users (id);

drop table if exists orders_details;
create table orders_details
(
    id         bigint         not null
        primary key,
    amount     decimal(19, 2) null,
    price      decimal(19, 2) null,
    order_id  bigint         null,
    product_id bigint         null

);
alter table orders_details
    add constraint orders_details_fk_orders
        foreign key (order_id) references orders (id);
  alter table orders_details
      add constraint orders_details_fk_products
          foreign key (product_id) references products (id);
create table orders_order_derails
(
    order_id         bigint not null,
    order_derails_id bigint not null


);

alter table orders_order_derails
    add constraint orders_order_details_uniq
        unique (order_derails_id);
 alter table orders_order_derails
    add constraint orders_order_derails_fk_orders_details
        foreign key (order_derails_id) references orders_details (id);
  alter table orders_order_derails
      add constraint orders_order_derails_fk_orders
          foreign key (order_id) references orders (id);

