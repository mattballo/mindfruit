package com.mattballo.mindfruit.controller;

import com.mattballo.mindfruit.annotation.ApiV1;
import com.mattballo.mindfruit.entity.Post;
import com.mattballo.mindfruit.entity.User;
import com.mattballo.mindfruit.service.JsonPlaceholderService;
import com.mattballo.mindfruit.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ApiV1
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class JsonPlaceholderController {

    @Autowired
    private JsonPlaceholderService service;

    @GetMapping("/users")
    public List<User> getAllPosts() {
        return service.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return service.getUser(id);
    }

    @GetMapping("/posts/external/{id}")
    public Post getPostById(@PathVariable Long id) {
        return service.getPost(id);
    }

}
