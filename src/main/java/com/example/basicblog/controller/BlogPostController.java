package com.example.basicblog.controller;

import com.example.basicblog.model.BlogPost;
import com.example.basicblog.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping(BlogPostController.CONTROLLER_ENDPOINT)
public class BlogPostController {

    final static String CONTROLLER_ENDPOINT = "/api/v1/posts";

    @Autowired
    private BlogPostService service;

    @GetMapping
    List<BlogPost> getAllPosts() {
        return service.findAllPosts();
    }

    @GetMapping("/{id}")
    BlogPost getPostById(@PathVariable Long id) {
        return service.findPostById(id);
    }
    @PostMapping
    BlogPost createPost(@RequestBody BlogPost newPost) {
        return service.save(newPost);
    }

    @DeleteMapping("/{id}")
    String deletePost(@PathVariable Long id, final RedirectAttributes redirectAttributes) {
        service.deletePost(id);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Post is deleted!");

        return "redirect:/" + BlogPostController.CONTROLLER_ENDPOINT;
    }
}
