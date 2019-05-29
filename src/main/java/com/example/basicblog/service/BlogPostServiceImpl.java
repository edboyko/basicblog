package com.example.basicblog.service;

import com.example.basicblog.model.BlogPost;
import com.example.basicblog.repository.BlogPostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BlogPostServiceImpl implements BlogPostService {

    private final BlogPostRepository repository;

    public BlogPostServiceImpl(BlogPostRepository repository) {
        this.repository = repository;
    }


    @Override
    public BlogPost findPostById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Post not found."));
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
                    p.setLastEditedDate(LocalDateTime.now());
                    return repository.save(p);
                }).orElseGet(() -> repository.save(post));
    }

    @Override
    public void deletePosts(List<BlogPost> posts) {
        repository.deleteAll(posts);
    }

    @Override
    public void deletePost(Long id) {
        repository.deleteById(id);
    }
}
