package com.mattballo.mindfruit.service;

import com.mattballo.mindfruit.entity.Post;
import com.mattballo.mindfruit.exception.NotFoundException;
import com.mattballo.mindfruit.repository.PostsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j // TODO: Remove
@Service
public class PostsService implements IPostsService {

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private JsonPlaceholderService jsonPlaceholderService;

    @Override
    public Post savePost(Post post) {
        if (post.getId() == null) {
            post.setId(postsRepository.getNextId());
        }
        return postsRepository.save(post);
    }

    @Override
    public List<Post> getAllPosts() {
        List<Post> posts = postsRepository.findAll();
        if (posts.isEmpty()) {
            throw new NotFoundException("No posts found in the database");
        }
        return posts;
    }

    @Override
    public List<Post> getPostByUserId(Long id) {
        List<Post> posts = postsRepository.findByUserId(id);
        if (posts.isEmpty()) {
            throw new NotFoundException("No posts found for user with id: " + id);
        }
        return posts;
    }


    @Override
    public Post getPost(Long id) {
        return postsRepository.findById(id)
                .orElseGet(() -> {
                    Post post = jsonPlaceholderService.getPost(id)
                            .orElseThrow(() ->
                                    new NotFoundException("Post with specified id: " + id + " does not exist")
                            );
                    post.setId(id);
                    log.warn("Post from external" + post.getId());
                    postsRepository.save(post);
                    return post;
                });
    }



    @Override
    public Post updatePost(Long id, Post post) {
        return postsRepository.findById(id)
                .map(originalPost -> {
                    // TODO: Remove ifs
                    if (post.getTitle() != null && !post.getTitle().isEmpty()) {
                        originalPost.setTitle(post.getTitle());
                    }
                    if (post.getBody() != null && !post.getBody().isEmpty()) {
                        originalPost.setBody(post.getBody());
                    }
                    return postsRepository.save(originalPost);
                })
                .orElseThrow(() -> new NotFoundException("Post with specified id: " + id + " does not exist"));
    }

    @Override
    public String deletePost(Long id) {
        if (postsRepository.findById(id).isPresent()) {
            postsRepository.deleteById(id);
            return "Post deleted successfully";
        }
        throw new NotFoundException("No such post in the database");
    }
}
