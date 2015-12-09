package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by jessicahuffstutler on 12/7/15.
 */@Entity
   @Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    public int id;

    @Column(nullable = false)
    public String firstName;

    @Column(nullable = false)
    public String lastName;

    @Column(nullable = false)
    public String username;

    @Column(nullable = false)
    public String password;

    @Column
    public String email;

    @Column(nullable = false)
    public boolean isAdmin;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
