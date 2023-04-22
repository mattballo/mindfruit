package com.mattballo.mindfruit.service;

import com.mattballo.mindfruit.entity.Post;
import com.mattballo.mindfruit.exception.NotFoundException;
import com.mattballo.mindfruit.repository.PostsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostsService implements IPostsService {

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private JsonPlaceholderService jsonPlaceholderService;

    @Override
    @Transactional
    public Post savePost(Post post) {
        post.setId(post.getId() == null ? postsRepository.getNextId() : post.getId());
        return postsRepository.save(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return Optional.of(postsRepository.findAll())
                .orElseThrow(() -> new NotFoundException("No posts found in the database"));
    }

    @Override
    public List<Post> getPostByUserId(Long id) {
        return Optional.of(postsRepository.findByUserId(id))
                .orElseThrow(() -> new NotFoundException("No posts found for user with id: " + id));
    }


    @Override
    @Transactional
    public Post getPost(Long id) {
        return postsRepository.findById(id)
                .orElseGet(() -> {
                    Post post = jsonPlaceholderService.getPost(id)
                            .orElseThrow(() ->
                                    new NotFoundException("Post with specified id: " + id + " does not exist")
                            );
                    post.setId(id);
                    postsRepository.save(post);
                    return post;
                });
    }


    @Override
    @Transactional
    public Post updatePost(Long id, Post post) {
        return postsRepository.findById(id)
                .map(originalPost -> {
                    if (post.getTitle() != null) {
                        originalPost.setTitle(post.getTitle());
                    }
                    if (post.getBody() != null) {
                        originalPost.setBody(post.getBody());
                    }
                    return postsRepository.save(originalPost);
                })
                .orElseThrow(() -> new NotFoundException("Post with specified id: " + id + " does not exist"));
    }

    @Override
    public String deletePost(Long id) {
        return postsRepository.findById(id)
                .map(post -> {
                    postsRepository.deleteById(id);
                    return "Post deleted successfully";
                })
                .orElseThrow(() -> new NotFoundException("No such post in the database"));
    }

}
