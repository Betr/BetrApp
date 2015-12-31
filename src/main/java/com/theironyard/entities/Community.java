package com.theironyard.entities;

import org.springframework.web.multipart.MultipartFile;

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
    public String name;

    @Column(nullable = false)
    public int population;

    @Column(nullable = false)
    public int goal;

    @Column(nullable = false)
    public String description;

    @Column(nullable = false)
    public String image;

    @Column(nullable = false)
    public int amount;

    //multiple communities per user
    @ManyToOne
    public User user;
    //public MultipartFile communityImage;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public int getGoal() {
        return goal;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public User getUser() {
        return user;
    }

    public int getAmount() {
        return amount;
    }
}
