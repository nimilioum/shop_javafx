package com.shop.core.users.Staff;

import com.shop.core.database.DBModel;
import com.shop.core.users.Customer.Customer;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class ShopStaff extends Staff{
    private boolean isSuperAdmin = false;

    public ShopStaff(String fname, String lname, String email, String username, String password, String nc,
                     String phoneNumber) throws Exception {
        super(fname, lname, email, username, password, nc, phoneNumber);
    }

    public boolean isSuperAdmin() {
        return isSuperAdmin;
    }

    public void setSuperAdmin(boolean superAdmin) {
        isSuperAdmin = superAdmin;
    }

    @Override
    public void save() throws Exception {
        String query = "call insertShopStaff(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        CallableStatement statement = connection.prepareCall(query);

        statement.setString("in_username", getUsername());
        statement.setString("in_email", getEmail());
        statement.setString("in_password", getPassword());
        statement.setString("in_first_name", getFname());
        statement.setString("in_last_name", getLname());
        statement.setString("in_phone", getPhoneNumber());
        statement.setString("in_nc", getNc());
        statement.setBoolean("super_admin", isSuperAdmin);
        statement.registerOutParameter("id", Types.INTEGER);

        statement.executeUpdate();
        setId(statement.getLong("id"));
    }

    public static ShopStaff find(String username, String password) throws Exception {
        String query = "call findShopStaff(?, ?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        statement.setString("in_username", username);
        statement.setString("in_password", password);

        ResultSet result = statement.executeQuery();
        ShopStaff staff = null;
        if (result.next()) {
            staff = new ShopStaff(result.getString("first_name"), result.getString("last_name"),
                    result.getString("email"), result.getString("username"),
                    result.getString("password"), result.getString("nc"), result.getString("phone_number"));
            staff.setId(result.getLong("id"));
        }
        return staff;
    }

    @Override
    public void delete() throws Exception {
        String query = "call deleteShopStaff(?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        statement.setLong("userId", id);
        statement.executeUpdate();
    }

    @Override
    public void updateUsername(String username) throws Exception {
        String query = "call updateShopStaffUserName(?, ?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        statement.setString("in_username", username);
        statement.setLong("userId", this.id);

        statement.executeUpdate();
        setUsername(username);
    }

    @Override
    public void updatePassword(String password) throws Exception {
        String query = "call updateShopStaffPassword(?, ?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        statement.setString("in_password", password);
        statement.setLong("userId", this.id);

        statement.executeUpdate();
        setPassword(password);
    }

    @Override
    public void updateEmail(String email) throws Exception {
        String query = "call updateShopStaffEmail(?, ?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        statement.setString("in_email", email);
        statement.setLong("userId", this.id);

        statement.executeUpdate();
        setEmail(email);
    }

    public void SetSuperAdmin(boolean status) throws Exception {
        String query = "call updateShopStaffStatus(?, ?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        statement.setBoolean("status", status);
        statement.setLong("userId", this.id);

        statement.executeUpdate();
        setSuperAdmin(status);
    }

    @Override
    public void updatePhone(String phone) throws Exception {
        String query = "call updateShopStaffPhone(?, ?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        statement.setString("in_phone", phone);
        statement.setLong("userId", this.id);

        statement.executeUpdate();
        setEmail(phone);
    }

    public static boolean usernameExists(String username) throws Exception {
        String query = "call shopStaffUsernameExists(?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        statement.setString("in_username", username);
        return statement.executeQuery().next();
    }

    public static boolean phoneNumberExists(String phoneNumber) throws Exception {
        String query = "call shopStaffPhoneExists(?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        statement.setString("in_phone", phoneNumber);
        return statement.executeQuery().next();
    }

    public static boolean emailExists(String email) throws Exception {
        String query = "call shopStaffEmailExists(?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        statement.setString("in_email", email);
        return statement.executeQuery().next();
    }
}
