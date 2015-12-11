package com.theironyard.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by jessicahuffstutler on 12/10/15.
 */
@Entity
@Table(name = "pressPosts")
public class Press {

    public String title;

    public String byline;

    public String link;

    public String imageLink; //or URL

    public String description;
}
