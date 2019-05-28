package com.example.basicblog.controller;

import com.example.basicblog.model.BlogPost;
import com.example.basicblog.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
public class BlogPostController {

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
    BlogPost deletePost(@PathVariable Long id) {
        return service.deletePost(id);
    }
}
