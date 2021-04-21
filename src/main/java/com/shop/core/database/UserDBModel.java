package com.shop.core.database;

import com.shop.core.users.Person;

import java.sql.SQLException;

public interface UserDBModel extends DBModel{

    public void updateUsername(String username) throws Exception;

    public void updatePassword(String password) throws Exception;

    public void updateEmail(String email) throws Exception;

    public void updatePhone(String phone) throws Exception;
    
}
