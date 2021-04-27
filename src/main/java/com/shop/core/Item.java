package com.shop.core;

import com.shop.core.database.DBModel;

import java.sql.ResultSet;

public class Item implements DBModel {
    private long id;
    private Product product;
    private int count;

    public Item(Product product, int count) {
        this.product = product;
        this.count = count;
    }

    public Item(ResultSet query) {

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


    @Override
    public void save() throws Exception {

    }

    @Override
    public void delete() throws Exception {

    }
}
