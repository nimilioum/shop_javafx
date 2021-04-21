package com.shop;

import com.shop.core.Category;
import com.shop.core.Product;
import com.shop.core.users.Customer.Customer;
import com.shop.core.users.Staff.InventoryStaff;
import com.shop.core.users.Staff.ShopStaff;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws Exception {
//        Customer customer = new Customer("nima", "saei", "a@a.co", "nima", "saei");
//        customer.save();
//        Customer customer = Customer.find("nima", "saei");
//        System.out.println(customer.getEmail());
//        InventoryStaff staff1 = new InventoryStaff("nima", "saei", "a@a.co", "nima", "saei",
//                "1234567890", "09141234123");
//        staff1.save();
        Product product1 = new Product("samsung 2", 1200, 5, "new mobile blah blah blah");
        Product product2 = new Product("apple 2", 1200, 5, "new mobile blah blah blah");

//        Category category = new Category("phone");

        product1.save();
        product2.save();
//        category.save();

//        product1.addCategory(category);
//
//        ArrayList<Product> products = Product.getProductsByCategory(category);
//        for (var product : products) {
//            System.out.println(product.getName());
//        }
    }
}
