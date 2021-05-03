drop procedure if exists insertShopStaff;
create procedure insertShopStaff(in in_username text, in in_email text, in in_password text, in_first_name text,
                                 in in_last_name text, in in_phone text, in in_nc text, in super_admin bool,
                                 out id integer)
begin
    insert into shop_staff(username, email, password, first_name, last_name, phone_number, nc, is_super_admin)
    values (in_username, in_email, SHA2(in_password, 256), in_first_name, in_last_name, in_phone, in_nc, super_admin);
    SELECT LAST_INSERT_ID() into id;
end;


# update statements
# ----------------------------------------------------------------------------------------

drop procedure if exists updateShopStaffUserName;
create procedure updateShopStaffUserName(in userName text, in userId integer)
begin
    update shop_staff set  username = userName where id = userId;
end;

drop procedure if exists updateShopStaffPassword;
create procedure updateShopStaffPassword(in in_password text, in userId integer)
begin
    update shop_staff set  password = sha2(in_password, 256) where id = userId;
end;

drop procedure if exists updateShopStaffEmail;
create procedure updateShopStaffEmail(in in_email text, in userId integer)
begin
    update shop_staff set  email = in_email where id = userId;
end;

drop procedure if exists updateShopStaffPhone;
create procedure updateShopStaffPhone(in in_phone text, in userId integer)
begin
    update shop_staff set  phone_number = in_phone where id = userId;
end;

drop procedure if exists updateShopStaffStatus;
create procedure updateShopStaffStatus(in status bool, in userId integer)
begin
    update shop_staff set  is_super_admin = status where id = userId;
end;


# delete statements
# ----------------------------------------------------------------------------------------


drop procedure if exists deleteShopStaff;
create procedure deleteShopStaff(in userId integer)
begin
    delete from shop_staff where id = userId;
end;


# select statements
# ----------------------------------------------------------------------------------------


drop procedure if exists findShopStaff;
create procedure findShopStaff(in in_username text, in in_password text)
begin
    select * from shop_staff where username = in_username and password = SHA2(in_password, 256) limit 1;
end;

drop procedure if exists findShopStaffById;
create procedure findShopStaffById(in userId integer)
begin
    select * from shop_staff where id = userId limit 1;
end;



# unique fields check

drop procedure if exists shopStaffUsernameExists;
create procedure shopStaffUsernameExists(in in_username text)
begin
    select * from shop_staff where username = in_username;
end;

drop procedure if exists shopStaffEmailExists;
create procedure shopStaffEmailExists(in in_email text)
begin
    select * from shop_staff where email = in_email;
end;

drop procedure if exists shopStaffPhoneExists;
create procedure shopStaffPhoneExists(in in_phone text)
begin
    select * from shop_staff where phone_number = in_phone;
end;

