package com.codeup.springblog.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Post {
    @Id @GeneratedValue
    private long id;

    @Column(nullable = false, length = 225)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;



    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
