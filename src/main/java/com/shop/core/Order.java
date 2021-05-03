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
    private String customerName;
    private DeliveryStaff deliverer;
    private Connection connection = null;

    public Order(Customer customer) throws Exception {
        this.creationDate = new Date();
        this.status = 0;
        this.customer = customer;
        customerName = customer.getFname() + " " + customer.getLname();
        connection = DBModel.setConnection();
    }

    public Order(ResultSet query) throws Exception {
        this.id = query.getLong("id");
        this.creationDate = new SimpleDateFormat("yyyy-MM-dd").parse(query.getString("creation_date"));
        this.status = query.getInt("status");
        if (status == 2)
        this.deliveryDate = new SimpleDateFormat("yyyy-MM-dd").parse(query.getString("delivery_date"));
        this.totalPrice = query.getDouble("price");
        this.customer = Customer.find(query.getLong("customer_id"));
        customerName = customer.getFname() + " " + customer.getLname();
        this.deliverer = DeliveryStaff.find(query.getLong("deliver_id"));

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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public ArrayList<Item> getItems() throws Exception {
        return Item.getOrderItems(this);
    }

    public static ArrayList<Order> getCustomerOrders(Customer customer) throws Exception {
        String query = "call getCustomerOrders(?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        statement.setLong("userId", customer.getId());

        ResultSet resultSet = statement.executeQuery();
        ArrayList<Order> orders = new ArrayList<>();

        while (resultSet.next()) orders.add(new Order(resultSet));
        return orders;
    }

    public static ArrayList<Order> getAllOrders() throws Exception {
        String query = "call getAllOrders()";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        ResultSet resultSet = statement.executeQuery();
        ArrayList<Order> orders = new ArrayList<>();

        while (resultSet.next()) orders.add(new Order(resultSet));
        return orders;
    }
}
