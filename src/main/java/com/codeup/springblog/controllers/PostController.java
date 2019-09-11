package com.codeup.springblog.controllers;


import com.codeup.springblog.daos.UserRepository;
import com.codeup.springblog.models.Post;
import com.codeup.springblog.daos.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {


    //    This is Dependency Injection, it belongs at the top of the controller.
private final PostRepository postDao;
private final UserRepository userDao;

public PostController(PostRepository postDao, UserRepository userDao) {
    this.postDao =  postDao ;
    this.userDao = userDao;
}




   //Create post
//    This URL is essentially where the HTML page to get to.
    @GetMapping("/posts/create")
    public String showCreateForm() {
//    This return should be the html page that you are going or requesting.
        return "posts/create";
    }

    //   This is the URL for the action in the form
    @PostMapping("/posts/create")
    public String createPost(
        @RequestParam(name = "title") String title,
        @RequestParam(name = "body") String body
        ) {

        Post postToCreate = new Post();
        postToCreate.setTitle(title);
        postToCreate.setBody(body);
        postDao.save(postToCreate);
        return "redirect:/posts";
    }

//  Show/read all
    @GetMapping("/posts")
    public String indexPosts(Model vModel){
    Iterable<Post> post = postDao.findAll();
        vModel.addAttribute("posts", post);
        return "posts/index";
    }

//    Show an individual by ID
    @GetMapping("/posts/{id}")
    public String showIndividualPost(@PathVariable long id, Model vModel) {
    Post post = postDao.findOne(id);
        vModel.addAttribute("post", post);
        return "posts/show";
    }

//  Edit/update post
    @GetMapping("/posts/{id}/edit")
    public String editPost(@PathVariable long id, Model vModel) {
        Post post = postDao.findOne(id);
        vModel.addAttribute("post", post);
        return "/posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String returnEditedPost(@PathVariable long id,
        @RequestParam(name = "title") String title,
        @RequestParam(name = "body") String body,
        Model vModel) {
    Post updatePost = postDao.findOne(id);
    updatePost.setTitle(title);
    updatePost.setBody(body);
    postDao.save(updatePost);
    return "redirect:/posts";
    }

//  Delete post
    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id){
        postDao.delete(id);
        return "redirect:/posts";
    }
}
