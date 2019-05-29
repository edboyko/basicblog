package com.example.basicblog.service;

import com.example.basicblog.model.BlogPost;

import java.util.List;

public interface BlogPostService {

    BlogPost findPostById(Long id);

    List<BlogPost> findAllPosts();

    BlogPost save(BlogPost post);

    void deletePosts(List<BlogPost> posts);

    void deletePost(Long id);
}
