package com.codeup.springblog.models;


import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {
    @Id @GeneratedValue
    private long id;

    @Column(nullable = false, length = 225)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

//  Used to create instance of a post. Dont forget.
    public Post() {}

//  Constructor for everything for the R of CRUD
    public Post(long id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

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
