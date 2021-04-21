package com.shop.core.database;

import com.shop.core.database.UserDBModel;

public interface CustomerDBModel extends UserDBModel {

    public void updateAddress(String address) throws Exception;
}
