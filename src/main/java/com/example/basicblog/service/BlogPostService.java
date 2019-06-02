package com.example.basicblog.service;

import com.example.basicblog.model.BlogPost;
import com.example.basicblog.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BlogPostService {

    BlogPost findPostById(Long id);

    List<BlogPost> findAllPosts();

    BlogPost save(BlogPost post, User user);

    void deletePosts(List<BlogPost> posts);

    void deletePost(Long id);

    Page<BlogPost> findPosts(Integer page, Integer numberOfResults);
}
