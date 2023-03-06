
create table buckets (
    id bigint not null auto_increment,
    user_id bigint,
     primary key (id)) engine=InnoDB;

create table buckets_products (
    bucket_id bigint not null,
    product_id bigint not null) engine=InnoDB;

create table orders (
    id bigint not null auto_increment,
    address varchar(255),
    created datetime(6),
    status varchar(255),
     sum decimal(19,2),
     updated datetime(6),
      user_id bigint,
      primary key (id)) engine=InnoDB;

create table orders_details (
    id bigint not null auto_increment,
     amount decimal(19,2),
      price decimal(19,2),
     order_id bigint,
     product_id bigint,
       primary key (id)) engine=InnoDB;
create table products (
    id bigint not null auto_increment,
     price double precision,
      title varchar(255),
      primary key (id)) engine=InnoDB;

create table users (
    id bigint not null auto_increment,
    active bit not null,
    email varchar(255),
    name varchar(255),
     password varchar(255),
      role varchar(255),
      primary key (id)) engine=InnoDB;


alter table buckets
    add constraint buckets_fk_user foreign key (user_id) references users (id);

alter table buckets_products
    add constraint buckets_products_fk_product foreign key (product_id) references products (id);

alter table buckets_products
    add constraint buckets_products_fk_bucket  foreign key (bucket_id) references buckets (id);

alter table orders
    add constraint order_fk_user foreign key (user_id) references users (id);

alter table orders_details
    add constraint orders_details_fk_order foreign key (order_id) references orders (id);

alter table orders_details
    add constraint orders_details_fk_product foreign key (product_id) references products (id);
