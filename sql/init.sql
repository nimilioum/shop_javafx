create database if not exists shop;
use shop;

create table if not exists customer(
    id integer primary key auto_increment unique not null,
    username text not null unique ,
    email text not null unique ,
    password text not null ,
    first_name text not null ,
    last_name text not null ,
    address text ,
    phone_number text unique
);

create table if not exists shop_staff (
    id integer primary key auto_increment unique not null,
    username text not null unique ,
    email text not null unique ,
    password text not null ,
    first_name text not null ,
    last_name text not null ,
    phone_number text unique not null ,
    nc text unique not null,
    is_super_admin bool default false
);

create table if not exists delivery_staff (
    id integer primary key auto_increment unique not null,
    username text not null unique ,
    email text not null unique ,
    password text not null ,
    first_name text not null ,
    last_name text not null ,
    phone_number text unique not null ,
    nc text unique not null
);

create table if not exists inventory_staff (
    id integer primary key auto_increment unique not null,
    username text not null unique ,
    email text not null unique ,
    password text not null ,
    first_name text not null ,
    last_name text not null ,
    phone_number text unique not null ,
    nc text unique not null
);

create table if not exists product (
    id integer primary key auto_increment unique not null,
    name text not null ,
    image_path text ,
    description text default '',
    price double not null default 0,
    count bigint not null default 0,
    sales bigint not null default 0
);

create table if not exists category (
    id integer primary key auto_increment unique not null,
    name text not null unique
);

create table if not exists product_category (
    product_id integer not null ,
    foreign key (product_id) references product(id) on delete cascade,
    category_id integer not null ,
    foreign key (category_id) references category(id) on DELETE cascade,
    primary key (product_id, category_id)
);

create table if not exists orders (
    id integer primary key auto_increment unique not null,
    customer_id integer not null ,
    foreign key (customer_id) references customer(id) on delete cascade ,

    creation_date text ,
    delivery_date text,
    price double not null ,
    status integer not null ,
    constraint chk check ( status in (0,1,2) ),

    deliver_id integer,
    foreign key (deliver_id) references delivery_staff(id) on delete set null
);

create table if not exists item(
    id integer primary key auto_increment unique not null,
    product_id integer not null ,
    foreign key (product_id) references product(id) on DELETE cascade ,
    order_id integer,
    foreign key (order_id) references orders(id) on DELETE cascade ,
    count integer default 0
);




# ----------------------------------------------------------------------------------------
# default admin
insert into shop_staff(username, email, password, first_name, last_name, phone_number, nc, is_super_admin)
    values ('admin', 'admin@shop.com', 'admin', 'none', 'none', 'none', 'none', true);


