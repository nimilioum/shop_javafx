drop procedure if exists insertDeliveryStaff;
create procedure insertDeliveryStaff(in in_username text, in in_email text, in in_password text, in_first_name text,
                                     in in_last_name text, in in_phone text, in in_nc text, out id integer)
begin
    insert into delivery_staff(username, email, password, first_name, last_name, phone_number, nc)
    values (in_username, in_email, SHA2(in_password, 256), in_first_name, in_last_name, in_phone, in_nc);
    SELECT LAST_INSERT_ID() into id;
end;


# update statements
# ----------------------------------------------------------------------------------------

drop procedure if exists updateDeliveryStaffUserName;
create procedure updateDeliveryStaffUserName(in userName text, in userId integer)
begin
    update inventory_staff set  username = userName where id = userId;
end;

drop procedure if exists updateDeliveryStaffPassword;
create procedure updateDeliveryStaffPassword(in in_password text, in userId integer)
begin
    update inventory_staff set  password = sha2(in_password, 256) where id = userId;
end;

drop procedure if exists updateDeliveryStaffEmail;
create procedure updateDeliveryStaffEmail(in in_email text, in userId integer)
begin
    update inventory_staff set  email = in_email where id = userId;
end;

drop procedure if exists updateDeliveryStaffPhone;
create procedure updateDeliveryStaffPhone(in in_phone text, in userId integer)
begin
    update inventory_staff set  phone_number = in_phone where id = userId;
end;


# delete statements
# ----------------------------------------------------------------------------------------


drop procedure if exists deleteDeliveryStaff;
create procedure deleteDeliveryStaff(in userId integer)
begin
    delete from delivery_staff where id = userId;
end;


# select statements
# ----------------------------------------------------------------------------------------


drop procedure if exists findDeliveryStaff;
create procedure findDeliveryStaff(in in_username text, in in_password text)
begin
    select * from delivery_staff where username = in_username and password = SHA2(in_password, 256) limit 1;
end;

drop procedure if exists findDeliveryStaffById;
create procedure findDeliveryStaffById(in userId integer)
begin
    select * from delivery_staff where id = userId limit 1;
end;




# unique fields check


drop procedure if exists deliveryStaffUsernameExists;
create procedure deliveryStaffUsernameExists(in in_username text)
begin
    select COUNT(*) from delivery_staff where username = in_username;
end;


drop procedure if exists deliveryStaffEmailExists;
create procedure deliveryStaffEmailExists(in in_email text)
begin
    select COUNT(*) from delivery_staff where email = in_email;
end;


drop procedure if exists deliveryStaffPhoneExists;
create procedure deliveryStaffPhoneExists(in in_phone text)
begin
    select COUNT(*) from delivery_staff where phone_number = in_phone;
end;

