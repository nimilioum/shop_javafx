package com.shop.core.users;

import java.sql.Connection;

abstract public class Person {
    protected long id;
    protected Connection connection;
    private String fname;
    private String lname;
    private String password, username, email;

    public Person(String fname, String lname,String email, String username, String password){
        this.fname = fname;
        this.lname = lname;
        this.password = password;
        this.username = username;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

//    public void setFname(String fname) {
//        this.fname = fname;
//    }

    public String getLname() {
        return lname;
    }

//    public void setLname(String lname) {
//        this.lname = lname;
//    }

    public String getPassword() {
        return password;
    }

    protected void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    protected void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    protected void setEmail(String email) {
        this.email = email;
    }
}
