package com.theironyard.entities;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    public String image;

    @Column(nullable = false)
    public String communityName;

    @Column(nullable = false)
    public String postName;

    @Column(nullable = false)
    public String postBody;

    //multiple posts per community
    @ManyToOne
    public Community community;
    //public MultipartFile postImage;


    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getCommunityName() {
        return communityName;
    }

    public String getPostName() {
        return postName;
    }

    public String getPostBody() {
        return postBody;
    }

    public Community getCommunity() {
        return community;
    }
}
