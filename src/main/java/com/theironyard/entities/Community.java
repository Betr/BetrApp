package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by jessicahuffstutler on 12/7/15.
 */
@Entity
@Table(name = "communities")
public class Community {
    @Id
    @GeneratedValue
    public int id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    int numberOfPeople;

    @Column(nullable = false)
    int goal;

    //multiple communities per user
    @ManyToOne
    public User user;
}
