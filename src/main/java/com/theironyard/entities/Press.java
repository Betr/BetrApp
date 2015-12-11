package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by jessicahuffstutler on 12/10/15.
 */
@Entity
@Table(name = "pressPosts")
public class Press {

    @Id
    @GeneratedValue
    public int id;

    @Column(nullable = false)
    public String title;

    @Column(nullable = false)
    public String byline;

    @Column(nullable = false)
    public String link;

    @Column(nullable = false)
    public String imageLink; //or URL

    @Column(nullable = false)
    public String description;
}
