package com.mattballo.mindfruit.controller;

import com.mattballo.mindfruit.entity.Post;
import com.mattballo.mindfruit.model.ApiResponse;
import com.mattballo.mindfruit.service.PostsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostsController {
    private final String MAPPING_URL = "/posts";

    @Autowired
    private PostsService postsService;

    @GetMapping
    public ApiResponse<String> get() {
        return new ApiResponse<>("Welcome to Mindfruit!");
    }

    @PostMapping(MAPPING_URL)
    public ApiResponse<Post> savePost(@Valid @RequestBody Post post) {
        return new ApiResponse<>(postsService.savePost(post));
    }

    @GetMapping(MAPPING_URL)
    public ApiResponse<List<Post>> getAllPosts(@RequestParam(value = "userId", required = false) Long userId) {
        return new ApiResponse<>(userId != null
                ? postsService.getPostByUserId(userId)
                : postsService.getAllPosts()
        );
    }

    @GetMapping(MAPPING_URL + "/{id}")
    public ApiResponse<Post> getPostById(@PathVariable Long id) {
        return new ApiResponse<>(postsService.getPost(id));
    }

    @PutMapping(MAPPING_URL + "/{id}")
    public ApiResponse<Post> updatePost(@PathVariable Long id, @RequestBody Post post) {
        return new ApiResponse<>(postsService.updatePost(id, post));
    }

    @DeleteMapping(MAPPING_URL + "/{id}")
    public ApiResponse<String> deletePost(@PathVariable Long id) { // TODO: Or only Response.OK?
        return new ApiResponse<>(postsService.deletePost(id));
    }

}