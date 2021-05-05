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



# update statements
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



# delete statements
# ----------------------------------------------------------------------------------------




# select statements
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