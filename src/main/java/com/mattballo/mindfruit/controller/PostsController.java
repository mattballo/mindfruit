package com.mattballo.mindfruit.controller;

import com.mattballo.mindfruit.entity.Post;
import com.mattballo.mindfruit.model.ApiResponse;
import com.mattballo.mindfruit.service.PostsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Posts")
public class PostsController {

    private final String MAPPING_URL = "/posts";

    @Autowired
    private PostsService postsService;

    @PostMapping(MAPPING_URL)
    @Operation(description = "Creates a new post with the provided details, and returns the newly created post. " +
            "Requires a valid user ID to be associated with the post.")
    public ApiResponse<Post> savePost(@Valid @RequestBody Post post) {
        return new ApiResponse<>(postsService.savePost(post));
    }

    @GetMapping(MAPPING_URL)
    @Operation(description = "Retrieves a list of all posts. If a user ID is provided as a query parameter, " +
            "retrieves a list of all posts associated with that user ID.")
    public ApiResponse<List<Post>> getAllPosts(@RequestParam(value = "userId", required = false) Long userId) {
        return new ApiResponse<>(userId != null
                ? postsService.getPostByUserId(userId)
                : postsService.getAllPosts()
        );
    }

    @GetMapping(MAPPING_URL + "/{id}")
    @Operation(description = "Retrieves a specific post by its ID.")
    public ApiResponse<Post> getPostById(@PathVariable Long id) {
        return new ApiResponse<>(postsService.getPost(id));
    }

    @PutMapping(MAPPING_URL + "/{id}")
    @Operation(description = "Updates a specific post by its ID, with the new details provided in the request body.")
    public ApiResponse<Post> updatePost(@PathVariable Long id, @RequestBody Post post) {
        return new ApiResponse<>(postsService.updatePost(id, post));
    }

    @DeleteMapping(MAPPING_URL + "/{id}")
    @Operation(description = "Deletes a specific post by its ID.")
    public ApiResponse<String> deletePost(@PathVariable Long id) {
        return new ApiResponse<>(postsService.deletePost(id));
    }

}