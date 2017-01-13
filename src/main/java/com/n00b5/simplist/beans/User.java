package com.n00b5.simplist.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.n00b5.simplist.api.Shopify.ShopifyToken;
import com.n00b5.simplist.api.ebay.EbayToken;
import com.n00b5.simplist.api.etsy.EtsyToken;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Project: Simplist
 *
 * @author Martino Nikolovski
 * @date 1/6/17
 */
@Entity
@SuppressWarnings("JpaDataSourceORMInspection")
@Table(name = "SIMPLIST_USER")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    @NotEmpty(message = "The first name cannot be null nor empty!")
    private String firstName;

    @Column
    private String lastName;

    @Column(nullable = false, unique = true)
    @Email(message = "Invalid email address!")
    @NotEmpty(message = "The email address cannot be null nor empty!")
    private String email;

    @Column(nullable = false)
    @NotEmpty
    private String password;

    @Column
    private String eBayRefreshToken;

    @Column
    private String etsyToken;

    @Column
    private String etsySecret;

    @Column
    private String shopifyToken;


    public User(String firstName,
                String lastName,
                String email,
                String password,
                String eBayRefreshToken,
                String etsyToken,
                String etsySecret,
                String shopifyToken) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.eBayRefreshToken = eBayRefreshToken;
        this.etsyToken = etsyToken;
        this.etsySecret = etsySecret;
        this.shopifyToken = shopifyToken;
    }

    public User() {
    }

    public String geteBayRefreshToken() {
        return eBayRefreshToken;
    }

    public void seteBayRefreshToken(String eBayRefreshToken) {
        this.eBayRefreshToken = eBayRefreshToken;
    }

    public String getEtsyToken() {
        return etsyToken;
    }

    public void setEtsyToken(String etsyToken) {
        this.etsyToken = etsyToken;
    }

    public String getEtsySecret() {
        return etsySecret;
    }

    public void setEtsySecret(String etsySecret) {
        this.etsySecret = etsySecret;
    }

    public String getShopifyToken() {
        return shopifyToken;
    }

    public void setShopifyToken(String shopifyToken) {
        this.shopifyToken = shopifyToken;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
