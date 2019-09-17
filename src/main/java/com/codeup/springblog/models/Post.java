package com.codeup.springblog.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Posts")
public class Post {

    @Id @GeneratedValue
    private long id;

    @Column(nullable = false, length = 225)
    @NotBlank(message = "Your post must include a title!")
    @Size(min = 3, message = "A title must have at least 3 characters.")
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "Your post must have some content, what's a post without any?")
    private String body;

    @OneToOne
    @JsonManagedReference
    private User owner;

    private String uploadPath;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Image> images;

    @Column
    private String file;

//  Used to create instance of a post. Dont forget.
    public Post() {}

//  Constructor for everything for the R of CRUD
    public Post(long id, String title, List<Image> images, String body, User owner, String file) {
        this.id = id;
        this.title = title;
        this.images = images;
        this.body = body;
        this.owner = owner;
        this.file = file;
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

    public String getFile() { return file; }

    public void setFile(String file) { this.file = file; }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }
}
