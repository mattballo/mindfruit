package com.mattballo.mindfruit.service;

import com.mattballo.mindfruit.entity.Post;

import java.util.List;

public interface IPostsService {

    Post savePost(Post post);

    List<Post> getAllPosts();

    Post getPost(Long id);

    Post updatePost(Long id, Post post);

    String deletePost(Long id);

    List<Post> getPostByUserId(Long id);
}
