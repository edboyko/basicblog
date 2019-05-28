package com.example.basicblog.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class BlogPost {

    private @GeneratedValue @Id long id;

    private String title;
    private String content;
    private final LocalDateTime createdDate = LocalDateTime.now();
    private int rating = 0;

    public BlogPost(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public BlogPost() {}

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public int getRating() {
        return rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
