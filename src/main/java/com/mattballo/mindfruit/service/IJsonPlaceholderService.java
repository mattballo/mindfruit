package com.mattballo.mindfruit.service;

import com.mattballo.mindfruit.entity.Post;
import com.mattballo.mindfruit.entity.User;

import java.util.List;
import java.util.Optional;

public interface IJsonPlaceholderService {

    Optional<List<User>> getAllUsers();

    Optional<User> getUser(Long id);

    Optional<User> getUserByEmail(String email);

    Optional<Post> getPost(Long id);
}
