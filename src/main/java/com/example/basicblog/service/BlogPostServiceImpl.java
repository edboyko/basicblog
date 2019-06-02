package com.example.basicblog.service;

import com.example.basicblog.model.BlogPost;
import com.example.basicblog.model.User;
import com.example.basicblog.repository.BlogPostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    public BlogPost save(BlogPost post, User user) {

        Optional<BlogPost> optionalPost = repository.findById(post.getId());
        BlogPost p;
        if (optionalPost.isPresent()) {
            p = optionalPost.get();
            p.setTitle(post.getTitle());
            p.setContent(post.getContent());
            p.setRating(post.getRating());
            p.setLastEditedDate(LocalDateTime.now());
        }
        else {
            p = post;
            p.setAuthorId(user.getId());
        }

        return repository.save(p);
    }

    @Override
    public void deletePosts(List<BlogPost> posts) {
        repository.deleteAll(posts);
    }

    @Override
    public void deletePost(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<BlogPost> findPosts(Integer page, Integer numberOfResults) {
        return repository.findAll(PageRequest.of(page, numberOfResults));
    }
}
