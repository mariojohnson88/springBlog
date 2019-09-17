package com.codeup.springblog.controllers;


import com.codeup.springblog.daos.PostRepository;
import com.codeup.springblog.daos.UserRepository;
import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private UserRepository users;
    private PasswordEncoder passwordEncoder;
    private PostRepository postRepository;

    public UserController(UserRepository users, PasswordEncoder passwordEncoder, PostRepository postRepository) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.postRepository = postRepository;
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model vModel){
        vModel.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        users.save(user);
        return "redirect:/login";
    }

    @GetMapping("/users-posts")
    public String ownerPosts(Model vModel) {
        User userSession = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Iterable<Post> posts = postRepository.findByOwner(userSession);
        vModel.addAttribute("posts", posts);
        return "users/users-posts";
    }

    @GetMapping("/contact-info")
    public String showContactPage(Model vModel) {
        return "users/contact-info";
    }
}
