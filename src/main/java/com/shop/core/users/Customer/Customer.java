package com.shop.core.users.Customer;

import com.shop.core.database.CustomerDBModel;
import com.shop.core.database.DBModel;
import com.shop.core.users.Person;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class Customer extends Person implements CustomerDBModel {
    private String address;
    String phoneNumber;

    public Customer(String fname, String lname, String email, String username, String password) throws Exception {
        super(fname, lname, email, username, password);
        connection = DBModel.setConnection();
    }

    public Customer(ResultSet result) throws Exception {
        this(result.getString("first_name"), result.getString("last_name"),
                result.getString("email"),
                result.getString("username"), result.getString("password"));
        setPhoneNumber(result.getString("phone_number"));
        setAddress("address");
        setId(result.getLong("id"));
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @Override
    public void save() throws SQLException {
        String query = "call insertCustomer(?, ?, ?, ?, ?, ?)";
        CallableStatement statement = connection.prepareCall(query);

        statement.setString("in_username", getUsername());
        statement.setString("in_email", getEmail());
        statement.setString("in_password", getPassword());
        statement.setString("in_first_name", getFname());
        statement.setString("in_last_name", getLname());
        statement.registerOutParameter("id", Types.INTEGER);

        statement.executeUpdate();
        setId(statement.getLong("id"));
    }


    public static Customer find(String username, String password) throws Exception {
        String query = "call findCustomer(?, ?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        statement.setString("in_username", username);
        statement.setString("in_password", password);

        ResultSet result = statement.executeQuery();
        Customer customer = null;
        if (result.next()) {
            customer = new Customer(result);
        }
        return customer;
    }

    public static Customer find(long id) throws Exception {
        String query = "call findCustomerById(?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        statement.setLong("userId", id);

        ResultSet result = statement.executeQuery();
        Customer customer = null;
        if (result.next()) {
            customer = new Customer(result);
        }
        return customer;
    }

    @Override
    public void updateUsername(String username) throws Exception {
        String query = "call updateCustomerUserName(?, ?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        statement.setString("userName", username);
        statement.setLong("userId", this.id);

        statement.executeUpdate();
        setUsername(username);
    }

    @Override
    public void updatePassword(String password) throws Exception {
        String query = "call updateCustomerPassword(?, ?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        statement.setString("in_password", password);
        statement.setLong("userId", this.id);

        statement.executeUpdate();
        setPassword(password);
    }

    @Override
    public void updateEmail(String email) throws Exception {
        String query = "call updateCustomerEmail(?, ?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        statement.setString("in_email", email);
        statement.setLong("userId", this.id);

        statement.executeUpdate();
        setEmail(email);
    }

    @Override
    public void updateAddress(String address) throws Exception {
        String query = "call updateCustomerAddress(?, ?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        statement.setString("in_address", address);
        statement.setLong("userId", this.id);

        statement.executeUpdate();
        setAddress(address);
    }

    @Override
    public void updatePhone(String phone) throws Exception {
        String query = "call updateCustomerPhone(?, ?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        statement.setString("in_phone", phone);
        statement.setLong("userId", this.id);

        statement.executeUpdate();
        this.phoneNumber = phone;
    }

    @Override
    public void delete() throws Exception {
        String query = "call deleteCustomer(?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        statement.setLong("userId", id);
        statement.executeUpdate();
    }

    public static boolean usernameExists(String username) throws Exception {
        String query = "call customerUsernameExists(?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        statement.setString("in_username", username);
        return statement.executeQuery().next();
    }

    public static boolean phoneNumberExists(String phoneNumber) throws Exception {
        String query = "call customerPhoneExists(?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        statement.setString("in_phone", phoneNumber);
        return statement.executeQuery().next();
    }

    public static boolean emailExists(String email) throws Exception {
        String query = "call customerEmailExists(?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        statement.setString("in_email", email);
        return statement.executeQuery().next();
    }

    @Override
    public String toString() {
        return getFname() + " " + getLname();
    }
}
