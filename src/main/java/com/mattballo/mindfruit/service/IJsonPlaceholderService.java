package com.mattballo.mindfruit.service;

import com.mattballo.mindfruit.entity.Post;
import com.mattballo.mindfruit.entity.User;

import java.util.List;

public interface IJsonPlaceholderService {

    List<User> getAllUsers();

    User getUser(Long id);

    User getUserByEmail(String email);

    Post getPost(Long id);
}
