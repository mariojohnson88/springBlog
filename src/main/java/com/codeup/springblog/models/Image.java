package com.codeup.springblog.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name ="Images")
public class Image {
    @Id @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String path;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private Post post;

    public Image(){}

    public Image(long id, String path, Post post) {
        this.id = id;
        this.path = path;
        this.post = post;
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getPath() { return path; }

    public void setPath(String path) { this.path = path; }

    public Post getPost() { return post; }

    public void setPost(Post post) { this.post = post; }
}
