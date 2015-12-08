package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by jessicahuffstutler on 12/7/15.
 */
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue
    public int id;

    @Column(nullable = false)
    public String communityName;

    @Column(nullable = false)
    public String postName;

    @Column(nullable = false)
    public String postBody;

    //multiple posts per community
    @ManyToOne
    public Community community;
}
