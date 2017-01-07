package com.n00b5.simplist.beans;

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
@Table(name = "SIMPLIST_USER")
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

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public User() {
    }


    /*
    @OneToOne(cascade = CascadeType.REMOVE)
    private ShopifyToken shopifyToken;

    @OneToOne(cascade = CascadeType.REMOVE)
    private EbayToken ebayToken;

    @OneToOne(cascade = CascadeType.REMOVE)
    private EtsyToken etsyToken;
*/

}
