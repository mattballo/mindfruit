package com.mattballo.mindfruit.controller;

import com.mattballo.mindfruit.annotation.ApiV1;
import com.mattballo.mindfruit.entity.Post;
import com.mattballo.mindfruit.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ApiV1
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostsController {

    @Autowired
    private PostsService postsService;

    @Autowired
    public void setClientService(PostsService postsService) {
        this.postsService = postsService;
    }

    @GetMapping
    public String get() {
        return "Welcome to Mindfruit!";
    }

    @PostMapping("/posts")
    public Post savePost(@RequestBody Post post) {
        return postsService.savePost(post);
    }

    @GetMapping("/posts")
    public List<Post> getAllPosts(@RequestParam(required = false) Long userId) {
        return userId != null ? postsService.getPostByUserId(userId) : postsService.getAllPosts();
    }

    @GetMapping("/posts/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postsService.getPost(id);
    }

    @PutMapping("/posts/{id}")
    public Post updatePost(@PathVariable("id") Long id, @RequestBody Post post) {
        return postsService.updatePost(id, post);
    }

    @DeleteMapping("/posts/{id}")
    public String deletePost(@PathVariable("id") Long id) {
        return postsService.deletePost(id);
    }
}