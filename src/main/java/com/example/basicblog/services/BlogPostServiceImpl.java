package com.example.basicblog.services;

import com.example.basicblog.model.BlogPost;
import com.example.basicblog.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BlogPostServiceImpl implements BlogPostService {

    private final BlogPostRepository repository;

    @Autowired
    public BlogPostServiceImpl(BlogPostRepository repository) {
        this.repository = repository;
    }


    @Override
    public BlogPost findPostById(Long id) {
        return repository.getOne(id);
    }

    @Override
    public List<BlogPost> findAllPosts() {
        return repository.findAll();
    }
}
