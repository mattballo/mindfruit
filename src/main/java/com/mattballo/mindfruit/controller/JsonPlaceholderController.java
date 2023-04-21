package com.mattballo.mindfruit.controller;

import com.mattballo.mindfruit.entity.Post;
import com.mattballo.mindfruit.entity.User;
import com.mattballo.mindfruit.exception.NotFoundException;
import com.mattballo.mindfruit.model.ApiResponse;
import com.mattballo.mindfruit.service.JsonPlaceholderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class JsonPlaceholderController {

    @Autowired
    private JsonPlaceholderService service;

    @GetMapping("/users")
    public ApiResponse<List<User>> getAllUsers() {
        return new ApiResponse<>(service
                .getAllUsers()
                .orElseThrow(() -> new NotFoundException("Users not found"))
        );
    }

    @GetMapping("/users/{id}")
    public ApiResponse<User> getUserById(@PathVariable Long id) {
        return new ApiResponse<>(service
                .getUser(id)
                .orElseThrow(() -> new NotFoundException("User with id: " + id + " not found"))
        );
    }

    @GetMapping("/posts/external/{id}")
    public ApiResponse<Post> getPostById(@PathVariable Long id) {
        return new ApiResponse<>(service
                .getPost(id)
                .orElseThrow(() -> new NotFoundException("Post with id: " + id + " not found"))
        );
    }

}
