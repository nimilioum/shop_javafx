

drop procedure if exists insertCustomer;
create procedure insertCustomer(in in_username text, in in_email text, in in_password text, in_first_name text,
                                in in_last_name text, out  id integer)
begin
    insert into customer(username, email, password, first_name, last_name)
    values (in_username, in_email, SHA2(in_password, 256), in_first_name, in_last_name);
    SELECT LAST_INSERT_ID() into id;
end;



# update statements
# ----------------------------------------------------------------------------------------


drop procedure if exists updateCustomerUserName;
create procedure updateCustomerUserName(in userName text, in userId integer)
begin
    update customer set  username = userName where id = userId;
end;

drop procedure if exists updateCustomerPassword;
create procedure updateCustomerPassword(in in_password text, in userId integer)
begin
    update customer set  password = sha2(in_password, 256) where id = userId;
end;

drop procedure if exists updateCustomerEmail;
create procedure updateCustomerEmail(in in_email text, in userId integer)
begin
    update customer set  email = in_email where id = userId;
end;

drop procedure if exists updateCustomerAddress;
create procedure updateCustomerAddress(in in_address text, in userId integer)
begin
    update customer set  address = in_address where id = userId;
end;

drop procedure if exists updateCustomerPhone;
create procedure updateCustomerPhone(in in_phone text, in userId integer)
begin
    update customer set  phone_number = in_phone where id = userId;
end;

# delete statements
# ----------------------------------------------------------------------------------------


drop procedure if exists deleteCustomer;
create procedure deleteCustomer(in userId integer)
begin
    delete from customer where id = userId;
end;


# select statements
# ----------------------------------------------------------------------------------------
drop procedure if exists findCustomer;
create procedure findCustomer(in in_username text, in in_password text)
begin
    select * from customer where username = in_username and password = SHA2(in_password, 256) limit 1;
end;

drop procedure if exists findCustomerById;
create procedure findCustomerById(in userId integer)
begin
    select * from customer where id = userId limit 1;
end;


# unique fields check

drop procedure if exists customerUsernameExists;
create procedure customerUsernameExists(in in_username text)
begin
    select * from customer where username = in_username;
end;

drop procedure if exists customerEmailExists;
create procedure customerEmailExists(in in_email text)
begin
    select * from customer where email = in_email;
end;

drop procedure if exists customerPhoneExists;
create procedure customerPhoneExists(in in_phone text)
begin
    select * from customer where phone_number = in_phone;
end;
