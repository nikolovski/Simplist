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
    private String first_name;

    @Column(name="LAST_NAME")
    @JsonProperty
    private String last_name;

    @Column(name="EMAIL")
    @Email
    @JsonProperty
    private String email;

    @Column(name="ACCOUNT_OWNER")
    @JsonProperty
    private boolean account_owner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAccount_owner() {
        return account_owner;
    }

    public void setAccount_owner(boolean account_owner) {
        this.account_owner = account_owner;
    }

    @Override
    public String toString() {
        return "ShopifyUser{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", account_owner=" + account_owner +
                '}';
    }
}
