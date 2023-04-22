package com.mattballo.mindfruit.controller;

import com.mattballo.mindfruit.entity.User;
import com.mattballo.mindfruit.exception.NotFoundException;
import com.mattballo.mindfruit.model.ApiResponse;
import com.mattballo.mindfruit.service.JsonPlaceholderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "JSON Placeholder")
public class JsonPlaceholderController {

    @Autowired
    private JsonPlaceholderService service;

    @GetMapping("/users")
    @Operation(description = "Retrieves a list of all users")
    public ApiResponse<List<User>> getAllUsers() {
        return new ApiResponse<>(service
                .getAllUsers()
                .orElseThrow(() -> new NotFoundException("Users not found"))
        );
    }

    @GetMapping("/users/{id}")
    @Operation(description = "Retrieves a specific user by its ID.")
    public ApiResponse<User> getUserById(@PathVariable Long id) {
        return new ApiResponse<>(service
                .getUser(id)
                .orElseThrow(() -> new NotFoundException("User with id: " + id + " not found"))
        );
    }

}
