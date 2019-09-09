package com.codeup.springblog.controllers;


import com.codeup.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class PostController {

    @GetMapping("/posts")
    public String allPosts(Model vModel) {

        ArrayList<Post> posts = new ArrayList<>();
        Post bike = new Post("Bike","I hate this bike, take it from me.");
        Post car = new Post("Honda Civic","2002 black Honda Civic.");
        Post cooler = new Post("Yeti Cooler","I don't need this, brand new.");

        posts.add(bike);
        posts.add(car);
        posts.add(cooler);

        vModel.addAttribute("posts", posts);
        return "posts/index";
    }


    @GetMapping("/posts/{id}")
    public String onePost(@PathVariable long id, Model vModel){

        Post crate = new Post("Dog crate", "Slightly used medium crate");
        vModel.addAttribute("post", crate);
        return "posts/show";
    }




//    @GetMapping("/posts")
//    @ResponseBody
//    public String indexPosts(){
//        return "Posts index page!";
//    }
//
//    @GetMapping("/posts/{id}")
//    @ResponseBody
//    public String individualPost(@PathVariable int id) {
//        return "View an post of user with id of " + id;
//    }

//    @GetMapping("/posts/create")
//    @ResponseBody
//    public String createPostForm(){
//        return "View the form for creating a post";
//    }
//
//    @PostMapping("/posts/create")
//    @ResponseBody
//    public String createPost(){
//        return "Create a post";
//    }

}
