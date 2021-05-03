package com.shop.core;

import com.shop.core.database.DBModel;

import java.sql.*;
import java.util.ArrayList;

public class Item implements DBModel {
    private long id;
    private Product product;
    private int count;
    private Order order;
    private Connection connection = null;

    public Item(Product product, int count) throws Exception {
        this.product = product;
        this.count = count;
        connection = DBModel.setConnection();
    }

    public Item(ResultSet query) throws Exception {
        this.count = query.getInt("count");
        this.product = Product.find(query.getLong("product_id"));

        connection = DBModel.setConnection();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public void save() throws Exception {
        String query = "call insertItem(?,?,?,?)";
        CallableStatement statement = connection.prepareCall(query);

        statement.setLong("productId", product.getId());
        statement.setLong("inCount", count);
        statement.setLong("orderId", order.getId());
        statement.registerOutParameter("id", Types.INTEGER);

        statement.executeUpdate();
        setId(statement.getLong("id"));

        product.productBought(count);
    }

    public static ArrayList<Item> getOrderItems(Order order) throws Exception {
        String query = "call getOrderItems(?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        statement.setLong("orderId", order.getId());
        ResultSet result = statement.executeQuery();
        ArrayList<Item> items = new ArrayList<>();
        while (result.next()) items.add(new Item(result));

        return items;
    }

    @Override
    public void delete() throws Exception {

    }
}
