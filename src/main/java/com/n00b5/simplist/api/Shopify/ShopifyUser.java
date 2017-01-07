package com.n00b5.simplist.api.Shopify;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;

/**
 * Project: Simplist
 *
 * @author Martino Nikolovski
 * @date 1/6/17
 */
@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "SHOPIFY_USER")
public class ShopifyUser {
    @Id
    @JsonProperty
    private int id;

    @Column(name="FIRST_NAME")
    @JsonProperty
    private String firstName;

    @Column(name="LAST_NAME")
    @JsonProperty
    private String lastName;

    @Column(name="EMAIL")
    @Email
    @JsonProperty
    private String email;

    @Column(name="ACCOUNT_OWNER")
    @JsonProperty
    private boolean accountOwner;

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

    public boolean isAccountOwner() {
        return accountOwner;
    }

    public void setAccountOwner(boolean accountOwner) {
        this.accountOwner = accountOwner;
    }

    @Override
    public String toString() {
        return "ShopifyUser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", accountOwner=" + accountOwner +
                '}';
    }
}
