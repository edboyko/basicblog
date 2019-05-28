package com.example.basicblog.service;

import com.example.basicblog.model.BlogPost;

import java.util.List;

public interface BlogPostService {

    BlogPost findPostById(Long id);

    List<BlogPost> findAllPosts();

    BlogPost save(BlogPost post);

    BlogPost deletePost(long id);
}
