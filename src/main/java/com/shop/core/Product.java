package com.shop.core;

import com.shop.core.database.DBModel;

import java.sql.*;
import java.util.ArrayList;

public class Product implements DBModel {
    private long id;
    private String name;
    private double price;
    private long count;
    private long sales;
    private String description;
    private String imagePath;
    private Connection connection = null;
//    private Category category;


    public Product(String name, double price, int count, String description) throws Exception {
        this.name = name;
        this.price = price;
        this.count = count;
        this.description = description;
        connection = DBModel.setConnection();
    }

    private Product(ResultSet query) throws Exception {
        this.id = query.getLong("id");
        this.name = query.getString("name");
        this.price = query.getDouble("price");
        this.count = query.getLong("count");
        this.sales = query.getLong("sales");
        this.description = query.getString("description");
        this.imagePath = query.getString("image_path");
        connection = DBModel.setConnection();
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public long getSales() {
        return sales;
    }

    public void setSales(long sales) {
        this.sales = sales;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void save() throws Exception {
        String query = "call insertProduct(?, ?, ?, ?, ?, ?)";
        CallableStatement statement = connection.prepareCall(query);

        statement.setString("inName", name);
        statement.setDouble("inPrice", price);
        statement.setLong("inCount", count);
        statement.setString("inImgPath", imagePath);
        statement.setString("inDescription", description);
        statement.registerOutParameter("id", Types.INTEGER);

        statement.executeUpdate();

        setId(statement.getLong("id"));
//        ResultSet keys =statement.getGeneratedKeys();
//        System.out.println(keys.next());
//        setId(keys.getLong(1));
        statement.close();
    }

    @Override
    public void delete() throws Exception {
        String query = "call deleteProduct(?)";
        CallableStatement statement = connection.prepareCall(query);

        statement.setLong("productId", id);
        statement.executeUpdate();
        statement.close();
    }

    public static ArrayList<Product> getAllProducts() throws Exception {
        String query = "call getAllProducts()";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);
        ArrayList<Product> products = new ArrayList<>();

        ResultSet resultSet = statement.executeQuery();
        while (!resultSet.isClosed() && resultSet.next()) {
            products.add(new Product(resultSet));
        }
        statement.close();
        return products;
    }

    public static ArrayList<Product> getProductsByCategory(Category category) throws Exception {
        String query = "call getProductsByCategory(?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);
        ArrayList<Product> products = new ArrayList<>();

        statement.setLong("category", category.getId());

        ResultSet resultSet = statement.executeQuery();
        while (! resultSet.isClosed() && resultSet.next() ) {
            System.out.println(resultSet.getString(1));
            products.add(new Product(resultSet));
        }
        statement.close();
        return products;
    }

    public static ArrayList<Product> getMostSoldProducts() throws Exception {
        String query = "call getMostSoldProducts()";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);
        ArrayList<Product> products = new ArrayList<>();

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            products.add(new Product(resultSet));
        }
        statement.close();
        return products;
    }

    public static ArrayList<Product> searchProduct(String name) throws Exception {
        String query = "call searchProduct(?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        statement.setString("in_name", name);
        ArrayList<Product> products = new ArrayList<>();

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            products.add(new Product(resultSet));
        }
        statement.close();
        return products;
    }

    public static ArrayList<Product> searchProductsAndCategories(String name) throws Exception {
        String query = "call searchProducts(?)";
        CallableStatement statement = DBModel.setConnection().prepareCall(query);

        statement.setString("in_name", name);

        ArrayList<Product> products = new ArrayList<>();

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            products.add(new Product(resultSet));
        }
        statement.close();
        return products;
    }

    public ArrayList<Category> getCategories() throws Exception {
        String query = "call getProductCategories(?)";
        CallableStatement statement = connection.prepareCall(query);
        ArrayList<Category> categories = new ArrayList<>();

        statement.setLong("productId", id);
        ResultSet result = statement.executeQuery();

        while (result.next()) categories.add(new Category(result));
        statement.close();

        return categories;
    }

    private void updatePrice() throws Exception {
        String query = "call updateProductPrice(?, ?)";
        CallableStatement statement = connection.prepareCall(query);

        statement.setDouble("newPrice", price);
        statement.setLong("productId", id);

        statement.executeUpdate();
        statement.close();
    }

    private void updateName() throws SQLException {
        String query = "call updateProductName(?, ?)";
        CallableStatement statement = connection.prepareCall(query);

        statement.setString("newName", name);
        statement.setLong("productId", id);

        statement.executeUpdate();
        statement.close();
    }

    private void updateImage() throws SQLException {
        String query = "call updateProductImage(?, ?)";
        CallableStatement statement = connection.prepareCall(query);

        statement.setString("newImage", imagePath);
        statement.setLong("productId", id);

        statement.executeUpdate();
        statement.close();
    }

    private void updateDescription() throws SQLException {
        String query = "call updateProductDescription(?, ?)";
        CallableStatement statement = connection.prepareCall(query);

        statement.setString("newDescription", description);
        statement.setLong("productId", id);

        statement.executeUpdate();
        statement.close();
    }

    private void updateSales() throws SQLException {
        String query = "call updateProductSales(?)";
        CallableStatement statement = connection.prepareCall(query);

        statement.setLong("productId", id);

        statement.executeUpdate();
        statement.close();
    }

    public void addCategory(Category category) throws SQLException {
        String query = "call insertProductCategory(?, ?)";
        CallableStatement statement = connection.prepareCall(query);

        statement.setLong("productId", id);
        statement.setLong("categoryId", category.getId());

        statement.executeUpdate();
        statement.close();
    }

}
