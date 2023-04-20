package com.mattballo.mindfruit.service;

import com.mattballo.mindfruit.entity.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

public interface IPostsService {

    Post savePost(Post post);

    List<Post> getAllPosts();

    Post getPost(Long id);

    Post updatePost(Long id, Post post);

    String deletePost(Long id);

    List<Post> getPostByUserId(Long id);
}
