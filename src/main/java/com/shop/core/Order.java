package com.shop.core;

import com.shop.core.database.DBModel;
import com.shop.core.users.Customer.Customer;
import com.shop.core.users.Staff.DeliveryStaff;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Order implements DBModel {
    private long id;
    private Date creationDate;
    private Date deliveryDate;
    private double totalPrice;
    private int status;

    private Customer customer;
    private DeliveryStaff deliverer;
    private Connection connection = null;

    public Order(Customer customer) throws Exception {
        this.creationDate = new Date();
        this.status = 0;
        this.customer = customer;
        connection = DBModel.setConnection();
    }

    public Order(ResultSet query) throws Exception {
        connection = DBModel.setConnection();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public DeliveryStaff getDeliverer() {
        return deliverer;
    }

    public void setDeliverer(DeliveryStaff deliverer) {
        this.deliverer = deliverer;
    }



    @Override
    public void save() throws Exception {
        String query = "call insertOrder(?,?,?,?)";
        CallableStatement statement = connection.prepareCall(query);

        statement.setLong("costumerId",customer.getId());
        statement.setString("createDate", new SimpleDateFormat("yyyy-MM-dd").format(creationDate));
        statement.setDouble("in_price", totalPrice);
        statement.registerOutParameter("id", Types.INTEGER);

        statement.executeUpdate();

        setId(statement.getLong("id"));

        statement.close();
    }

    public void addItems(ArrayList<Item> items) throws Exception {

        for (var item : items) {
            item.setOrder(this);
            item.save();
        }

    }

    @Override
    public void delete() throws Exception {

    }
}
