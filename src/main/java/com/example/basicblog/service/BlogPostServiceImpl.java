package com.example.basicblog.service;

import com.example.basicblog.model.BlogPost;
import com.example.basicblog.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    @Override
    public BlogPost save(BlogPost post) {
        return repository.findById(post.getId()).map(
                p -> {
                    p.setTitle(post.getTitle());
                    p.setContent(post.getContent());
                    p.setRating(post.getRating());
                    return repository.save(p);
                }).orElseGet(() -> repository.save(post));
    }
}
