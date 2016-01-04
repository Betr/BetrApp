package com.theironyard.entities;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.math.BigDecimal;

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

    @Column(nullable = true)
    public BigDecimal amount;

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

    public BigDecimal getAmount() {
        return amount;
    }
}
