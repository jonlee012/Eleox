package com.example.eleox.controllers;

import com.example.eleox.repositories.PostRepository;
import com.example.eleox.repositories.UserRepository;
import com.example.eleox.models.Users;
import com.example.eleox.servicies.EmailService;
import com.example.eleox.models.Post;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;
    private final EmailService emailService;

    // setting up the post controller
    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    // getting the show post page
    @GetMapping("/posts")
    public String posts(Model model) {
        model.addAttribute("post", postDao.findAll());
        model.addAttribute("user", userDao.findAll());
        return "posts/show";
    }

    // delete a post method
    @PostMapping("/posts/{id}")
    public String deletePost(@PathVariable Long id) {
        postDao.deleteById(id);
        return "redirect:/posts";
    }

    @GetMapping("posts/edit/{id}")
    public String editPost(@PathVariable long id, Model model) {
        Post editPost = postDao.getById(id);
        model.addAttribute("postToEdit", editPost);
        return "posts/edit";
    }

    @PostMapping("/posts/edit/{id}")
    public String saveEditedPost(@PathVariable long id, @RequestParam(name="postTitle") String postTitle, @RequestParam(name="postBody") String postBody) {
        Post postToEdit = postDao.getById(id);
        postToEdit.setBody(postBody);
        postToEdit.setTitle(postTitle);
        postDao.save(postToEdit);
        return "redirect:/posts";
    }

    @GetMapping("/posts/create")
    public String viewCreatePost(Model model) {
        model.addAttribute("post", new Post());
        return "/posts/create";
    }

    @PostMapping("/posts/create")
    public String create(@ModelAttribute Post post) {
        Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(user);
        emailService.prepareAndSend(post, "Your post has been created", "Your latest blog post is up and ready to view!");
        postDao.save(post);
        return "redirect:/posts";
    }

}
