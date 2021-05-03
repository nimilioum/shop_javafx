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


drop procedure if exists insertOrder;
create procedure insertOrder(in costumerId integer, in createDate text, in in_price integer, out id integer)
begin
    insert into orders(customer_id, creation_date, status, price) values
                    (costumerId, createDate, 0, in_price);
    SELECT LAST_INSERT_ID() into id;
end;

drop procedure if exists insertItem;
create procedure insertItem(in productId integer, in inCount integer, in orderId integer, out id integer)
begin
    insert into item(product_id, count, order_id) VALUES (productId, inCount, orderId);
    SELECT LAST_INSERT_ID() into id;
end;







# ----------------------------------------------------------------------------------------



drop procedure if exists updateOrderStatus;
create procedure updateOrderStatus(in in_status integer, in orderId integer)
begin
    update orders set status = in_status where id = orderId;
end;



drop procedure if exists updateOrderDeliverDate;
create procedure updateOrderDeliverDate(in in_date text, in orderId integer)
begin
    update orders set delivery_date = in_date where id = orderId;
end;

drop procedure if exists updateOrderDeliverer;
create procedure updateOrderDeliverer(in in_deliver integer, in orderId integer)
begin
    update orders set deliver_id = in_deliver where id = orderId;
end;


# ----------------------------------------------------------------------------------------





# ----------------------------------------------------------------------------------------


drop procedure if exists getCustomerOrders;
create procedure getCustomerOrders(in userId integer)
begin
    select * from orders where customer_id = userId;
end;

drop procedure if exists getDelivererOrders;
create procedure getDelivererOrders(in userId integer)
begin
    select * from orders where deliver_id = userId and status != 2;
end;

drop procedure if exists getAllOrders;
create procedure getAllOrders()
begin
    select * from orders order by -id;
end;

drop procedure if exists getUndoneOrders;
create procedure getUndoneOrders()
begin
    select * from orders where status != 2;
end;


drop procedure if exists getOrderItems;
create procedure getOrderItems(in orderId integer)
begin
    select * from item where order_id = orderId;
end;

