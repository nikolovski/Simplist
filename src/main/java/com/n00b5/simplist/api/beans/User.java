package com.n00b5.simplist.api.beans;

/**
 * Created by Shehar on 1/5/2017.
 */
public class User {
    private int number;
    private String fname;
    private String lname;
    private String email;
    private String password;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(int number, String fname, String lname, String email, String password) {

        this.number = number;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
    }
}
