package com.theironyard.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    public LocalDateTime time;

    public LocalDateTime getTime() {

        return time;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getByline() {
        return byline;
    }

    public String getLink() {
        return link;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getDescription() {
        return description;
    }
}
