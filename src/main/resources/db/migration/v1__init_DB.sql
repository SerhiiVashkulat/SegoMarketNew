#
# drop table if exists buckets
# create table buckets
# (
#     id      bigint not null
#         primary key,
#     user_id bigint null
# );
# drop table if exists buckets_products;
# create table buckets_products
# (
#     bucket_id  bigint not null,
#     product_id bigint not null
#
# );
# drop table if exists categories;
# create table categories
# (
#     id    bigint       not null
#         primary key,
#     title varchar(255) null
# );
# drop table if exists orders;
# create table orders
# (
#     id           bigint         not null
#         primary key,
#     address      varchar(255)   null,
#     created      datetime(6)    null,
#     order_status varchar(255)   null,
#     sum          decimal(19, 2) null,
#     updated      datetime(6)    null,
#     user_id      bigint         null
#
# );
# drop table if exists orders_details;
# create table orders_details
# (
#     id         bigint         not null
#         primary key,
#     amount     decimal(19, 2) null,
#     price      decimal(19, 2) null,
#     order_id  bigint         null,
#     product_id bigint         null
#
# );
# drop table if exists products;
# create table products
# (
#     id    bigint         not null
#         primary key,
#     price decimal(19, 2) null,
#     title varchar(255)   null
# );
# drop table if exists products_categories;
# create table products_categories
# (
#     product_id  bigint not null,
#     category_id bigint not null
#
# );
#
#
# create table flyway_schema_history
# (
#     installed_rank int                                 not null
#         primary key,
#     version        varchar(50)                         null,
#     description    varchar(200)                        not null,
#     type           varchar(20)                         not null,
#     script         varchar(1000)                       not null,
#     checksum       int                                 null,
#     installed_by   varchar(100)                        not null,
#     installed_on   timestamp default CURRENT_TIMESTAMP not null,
#     execution_time int                                 not null,
#     success        tinyint(1)                          not null
# );
# create index flyway_schema_history_s_idx
#     on flyway_schema_history (success);
#
# # alter table buckets_products drop foreign key FKloyxdc1uy11tayedf3dpu9lci;
# # alter table buckets_products drop foreign key FKc49ah45o66gy2f2f4c3os3149;
# #  alter table orders drop foreign key FK32ql8ubntj5uh44ph9659tiih;
# # alter table orders_details drop foreign key FK5o977kj2vptwo70fu7w7so9fe;
# # alter table orders_details drop foreign key FKs0r9x49croribb4j6tah648gt;
# # alter table orders_details drop foreign key FKgvp1k7a3ubdboj3yhnawd5m1p;
# #  alter table products_categories drop foreign key FKqt6m2o5dly3luqcm00f5t4h2p;
# # alter table products_categories drop foreign key FKtj1vdea8qwerbjqie4xldl1el;
# # alter table users drop foreign key FK8l2qc4c6gihjdyoch727guci;
#
# alter table orders_details
#     add constraint orders_details_fk unique (details_id);
#
# alter table buckets
#     add constraint bucket_fk_user
#         foreign key (user_id) references users (id);
#
# alter table buckets_products
#     add constraint buckets_products_fk_products
#         foreign key (product_id) references products (id);
#
# alter table buckets_products
#     add constraint buckets_products_fk_bucket
#         foreign key (bucket_id) references buckets (id);
#
# alter table orders
#     add constraint orders_fk_user
#         foreign key (user_id) references users (id);
#
# alter table orders_details
#     add constraint orders_details_fk_orders
#         foreign key (order_id) references orders (id);
#
# alter table orders_details
#     add constraint orders_details_fk_products
#         foreign key (product_id) references products (id);
#
# alter table orders_details
#     add constraint orders_details_fk_details
#         foreign key (details_id) references orders_details (id);
#
# alter table products_categories
#     add constraint products_categories_fk_category
#         foreign key (category_id) references categories (id);
#
# alter table products_categories
#     add constraint products_categories_fk_products
#         foreign key (product_id) references products (id);
#
# alter table users
#     add constraint users_fk_bucket
#     foreign key (bucket_id) references buckets (id);
#
#
#
#
#
#
#
#
#
#
