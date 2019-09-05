package com.codeup.springblog.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String indexPosts(){
        return "Posts index page!";
    }


    int id = 1;
    @GetMapping("/posts/{id}")
    @ResponseBody
    public String individualPost(@PathVariable int id) {
        return "View an individual post";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String createPostForm(){
        return "View the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPosts(){
        return "Create a post";
    }

}
