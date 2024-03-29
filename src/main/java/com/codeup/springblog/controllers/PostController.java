package com.codeup.springblog.controllers;


import com.codeup.springblog.Services.EmailService;
import com.codeup.springblog.daos.UserRepository;
import com.codeup.springblog.models.Post;
import com.codeup.springblog.daos.PostRepository;
import com.codeup.springblog.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PostController {


    //    This is Dependency Injection, it belongs at the top of the controller.
private final PostRepository postDao;
private final UserRepository userDao;
private final EmailService emailService;

public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService) {
    this.postDao =  postDao ;
    this.userDao = userDao;
    this.emailService = emailService;
}


// Using @Autowired is also a way to use Dependency Injection.
//@Autowired
//private EmailService emailService;


   //Create post
//    This URL is essentially where the HTML page to get to.
    @GetMapping("/posts/create")
    public String showCreateForm(Model vModel) {
    vModel.addAttribute("post", new Post());
//    This return should be the html page that you are going or requesting.
        return "posts/create";
    }

    //   This is the URL for the action in the form
    @PostMapping("/posts/create")
    public String createPost(
            @Valid Post post,
            Errors validation,
            Model vModel
        ) {
        if (validation.hasErrors()) {
            vModel.addAttribute("errors", validation);
            vModel.addAttribute("post", post);
            System.out.println(validation);
            return "posts/create";
        } else {

            User userSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User userDB = userDao.findOne(userSession.getId());
            post.setOwner(userDB);
            Post savedPost = postDao.save(post);
            emailService.prepareAndSend(savedPost, "Post Created", String.format("A post with the id %d has been posted", savedPost.getId()));
            return "redirect:/posts";
        }
    }

//  Show/read all
    @GetMapping("/posts")
    public String indexAllPosts(Model vModel){
    Iterable<Post> post = postDao.findAll();
        vModel.addAttribute("posts", post);
        return "posts/index";
    }

//    Show an individual post by ID
    @GetMapping("/posts/{id}")
    public String showSinglePostById(@PathVariable long id, Model vModel) {
    Post post = postDao.findOne(id);
        vModel.addAttribute("post", post);
        return "posts/show";
    }

//  Edit/update post, the ID here is the posts ID, not the user
    @GetMapping("/posts/{id}/edit")
    public String editPostById(@PathVariable long id, Model vModel) {
        Post post = postDao.findOne(id);
        vModel.addAttribute("post", post);
        return "/posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String returnEditedPost(@ModelAttribute Post newPost) {
    postDao.save(newPost);
    return "redirect:/posts";
    }

//  Delete post
    @PostMapping("/posts/{id}/delete")
    public String deletePostById(@PathVariable long id){
        postDao.delete(id);
        return "redirect:/users-posts";
    }

//
    @GetMapping("/posts/ajax")
    public String viewAllAdsWithAjax() {
    return "posts/ajax";
    }


    @GetMapping("/posts.json")
    public @ResponseBody
    List<Post> viewAllAdsInJSONFormat() {
        return (List<Post>) postDao.findAll();
    }

}
