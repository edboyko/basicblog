package com.example.basicblog.controller;

import com.example.basicblog.model.BlogPost;
import com.example.basicblog.service.BlogPostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Api(value = "Blog Posts Management System", description = "Contains CRUD methods for Blog Posts.")
@RestController
@RequestMapping(BlogPostController.CONTROLLER_ENDPOINT)
public class BlogPostController {

    final static String CONTROLLER_ENDPOINT = "/api/v1/posts";

    @Autowired
    private BlogPostService service;

    @ApiOperation(value = "Show all existing posts.", response = List.class)
    @GetMapping
    List<BlogPost> getAllPosts() {
        return service.findAllPosts();
    }

    @ApiOperation(value = "Find post by it's id.", response = BlogPost.class)
    @GetMapping("/{id}")
    BlogPost getPostById(@PathVariable Long id) {
        return service.findPostById(id);
    }

    @ApiOperation(value = "Create new post or update existing if 'id' value have been specified and post with the " +
            "same id has been found.", response = BlogPost.class)
    @PostMapping
    BlogPost createPost(@RequestBody BlogPost post) {
        return service.save(post);
    }

    @ApiOperation(value = "Delete existing post by id.")
    @DeleteMapping("/{id}")
    String deletePost(@PathVariable Long id, final RedirectAttributes redirectAttributes) {
        service.deletePost(id);

        redirectAttributes.addFlashAttribute("css", "success");
        redirectAttributes.addFlashAttribute("msg", "Post is deleted!");

        return "redirect:/" + BlogPostController.CONTROLLER_ENDPOINT;
    }
}
