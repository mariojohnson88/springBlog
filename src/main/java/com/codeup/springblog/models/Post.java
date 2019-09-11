package com.codeup.springblog.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Posts")
public class Post {

    @Id @GeneratedValue
    private long id;

    @Column(nullable = false, length = 225)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

    @OneToOne
    private User owner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Image> images;

//  Used to create instance of a post. Dont forget.
    public Post() {}

//  Constructor for everything for the R of CRUD
    public Post(long id, String title, List<Image> images, String body, User owner) {
        this.id = id;
        this.title = title;
        this.images = images;
        this.body = body;
        this.owner = owner;
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Image> getImages() { return images; }

    public void setImages(List<Image> images) { this.images = images; }

    public String getBody() { return body; }

    public void setBody(String body) { this.body = body; }

    public User getOwner() { return owner; }

    public void setOwner(User owner) { this.owner = owner; }

}
