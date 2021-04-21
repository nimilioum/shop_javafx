package com.shop.core.users.Staff;

import com.shop.core.database.DBModel;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;

public class InventoryStaff extends Staff{

    public InventoryStaff(String fname, String lname, String email, String username, String password, String nc,
                          String phoneNumber) throws Exception {
        super(fname, lname, email, username, password, nc, phoneNumber);
    }

    @Override
    public void save() throws Exception {
        String query = "call insertInventoryStaff(?, ?, ?, ?, ?, ?, ?, ?)";
        CallableStatement statement = connection.prepareCall(query);

        statement.setString("in_username", getUsername());
        statement.setString("in_email", getEmail());
        statement.setString("in_password", getPassword());
        statement.setString("in_first_name", getFname());
        statement.setString("in_last_name", getLname());
        statement.setString("in_phone", getPhoneNumber());
        statement.setString("in_nc", getNc());
        statement.registerOutParameter("id", Types.INTEGER);

        statement.executeUpdate();
        setId(statement.getLong("id"));
    }

    public static InventoryStaff find(String username, String password) throws Exception {
        String query = "call findInventoryStaff(?, ?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        statement.setString("in_username", username);
        statement.setString("in_password", password);

        ResultSet result = statement.executeQuery();
        InventoryStaff staff = null;
        if (result.next()) {
            staff = new InventoryStaff(result.getString("first_name"), result.getString("last_name"),
                    result.getString("email"), result.getString("username"),
                    result.getString("password"), result.getString("nc"), result.getString("phone_number"));
            staff.setId(result.getLong("id"));
        }
        return staff;
    }

    @Override
    public void delete() throws Exception {
        String query = "call deleteInventoryStaff(?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        statement.setLong("userId", id);
        statement.executeUpdate();
    }

    @Override
    public void updateUsername(String username) throws Exception {
        String query = "call updateInventoryStaffUserName(?, ?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        statement.setString("in_email", username);
        statement.setLong("userId", this.id);

        statement.executeUpdate();
        setUsername(username);
    }

    @Override
    public void updatePassword(String password) throws Exception {
        String query = "call updateInventoryStaffPassword(?, ?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        statement.setString("in_email", password);
        statement.setLong("userId", this.id);

        statement.executeUpdate();
        setPassword(password);
    }

    @Override
    public void updateEmail(String email) throws Exception {
        String query = "call updateInventoryStaffEmail(?, ?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        statement.setString("in_email", email);
        statement.setLong("userId", this.id);

        statement.executeUpdate();
        setEmail(email);
    }

    @Override
    public void updatePhone(String phone) throws Exception {
        String query = "call updateInventoryStaffPhone(?, ?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        statement.setString("in_email", phone);
        statement.setLong("userId", this.id);

        statement.executeUpdate();
        setEmail(phone);
    }

    public static boolean usernameExists(String username) throws Exception {
        String query = "call inventoryStaffUsernameExists(?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        statement.setString("in_username", username);
        return statement.executeQuery().next();
    }

    public static boolean phoneNumberExists(String phoneNumber) throws Exception {
        String query = "call inventoryStaffPhoneExists(?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        statement.setString("in_phone", phoneNumber);
        return statement.executeQuery().next();
    }

    public static boolean emailExists(String email) throws Exception {
        String query = "call inventoryStaffEmailExists(?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        statement.setString("in_phone", email);
        return statement.executeQuery().next();
    }
}
