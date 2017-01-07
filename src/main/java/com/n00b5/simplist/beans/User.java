package com.n00b5.simplist.beans;

import com.n00b5.simplist.api.Shopify.ShopifyToken;
import com.n00b5.simplist.api.ebay.EbayToken;
import com.n00b5.simplist.api.etsy.EtsyToken;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Project: Simplist
 *
 * @author Martino Nikolovski
 * @date 1/6/17
 */
@Entity
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

    @OneToOne(cascade = CascadeType.REMOVE)
    private ShopifyToken shopifyToken;

    @OneToOne(cascade = CascadeType.REMOVE)
    private EbayToken ebayToken;

    @OneToOne(cascade = CascadeType.REMOVE)
    private EtsyToken etsyToken;


}
