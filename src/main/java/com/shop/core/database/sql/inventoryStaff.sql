drop procedure if exists insertInventoryStaff;
create procedure insertInventoryStaff(in in_username text, in in_email text, in in_password text, in_first_name text,
                                      in in_last_name text, in in_phone text, in in_nc text, out id integer)
begin
    insert into inventory_staff(username, email, password, first_name, last_name, phone_number, nc)
    values (in_username, in_email, SHA2(in_password, 256), in_first_name, in_last_name, in_phone, in_nc);
    SELECT LAST_INSERT_ID() into id;
end;

# update statements
# ----------------------------------------------------------------------------------------

drop procedure if exists updateInventoryStaffUserName;
create procedure updateInventoryStaffUserName(in userName text, in userId integer)
begin
    update inventory_staff set  username = userName where id = userId;
end;

drop procedure if exists updateInventoryStaffPassword;
create procedure updateInventoryStaffPassword(in in_password text, in userId integer)
begin
    update inventory_staff set  password = sha2(in_password, 256) where id = userId;
end;

drop procedure if exists updateInventoryStaffEmail;
create procedure updateInventoryStaffEmail(in in_email text, in userId integer)
begin
    update inventory_staff set  email = in_email where id = userId;
end;

drop procedure if exists updateInventoryStaffPhone;
create procedure updateInventoryStaffPhone(in in_phone text, in userId integer)
begin
    update inventory_staff set  phone_number = in_phone where id = userId;
end;


# delete statements
# ----------------------------------------------------------------------------------------


drop procedure if exists deleteInventoryStaff;
create procedure deleteInventoryStaff(in userId integer)
begin
    delete from inventory_staff where id = userId;
end;


# select statements
# ----------------------------------------------------------------------------------------


drop procedure if exists findInventoryStaff;
create procedure findInventoryStaff(in in_username text, in in_password text)
begin
    select * from inventory_staff where username = in_username and password = SHA2(in_password, 256) limit 1;
end;


# unique fields check

drop procedure if exists inventoryStaffUsernameExists;
create procedure inventoryStaffUsernameExists(in in_username text)
begin
    select COUNT(*) from inventory_staff where username = in_username;
end;

drop procedure if exists inventoryStaffEmailExists;
create procedure inventoryStaffEmailExists(in in_email text)
begin
    select COUNT(*) from inventory_staff where email = in_email;
end;

drop procedure if exists inventoryStaffPhoneExists;
create procedure inventoryStaffPhoneExists(in in_phone text)
begin
    select COUNT(*) from inventory_staff where phone_number = in_phone;
end;