package com.shop.core.users.Staff;
import com.shop.core.database.DBModel;
import com.shop.core.database.StaffDBModel;
import com.shop.core.users.Person;

abstract public class Staff extends Person implements StaffDBModel {
    private String nc;
    private String phoneNumber;

    public Staff(String fname, String lname, String email, String username, String password, String nc,
                 String phoneNumber) throws Exception {
        super(fname, lname, email, username, password);
        this.nc = nc;
        this.phoneNumber = phoneNumber;
        connection = DBModel.setConnection();
    }

    public String  getNc() {
        return nc;
    }

    public void setNc(String nc) {
        this.nc = nc;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
