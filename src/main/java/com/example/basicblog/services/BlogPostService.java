package com.example.basicblog.services;

import com.example.basicblog.model.BlogPost;

import java.util.List;

public interface BlogPostService {

    BlogPost findPostById(Long id);

    List<BlogPost> findAllPosts();
}
