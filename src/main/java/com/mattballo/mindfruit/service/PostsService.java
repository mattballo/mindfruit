package com.mattballo.mindfruit.service;

import com.mattballo.mindfruit.entity.Post;
import com.mattballo.mindfruit.repository.PostsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PostsService implements IPostsService {
    @Autowired
    private PostsRepository postsRepository;

    @Override
    public Post savePost(Post post) {
        return postsRepository.save(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postsRepository.findAll();
    }

    @Override
    public List<Post> getPostByUserId(Long id) {
        return postsRepository.findByUserId(id);
    }

    @Override
    public Post getPost(Long id) {
        return postsRepository.getReferenceById(id);
    }

    @Override
    public Post updatePost(Long id, Post post) {
        Optional<Post> currentPost = postsRepository.findById(id);

        if (currentPost.isPresent()) {
            Post originalPost = currentPost.get();
            if (post.getTitle() != null && !post.getTitle().isEmpty()) {
                originalPost.setTitle(post.getTitle());
            }
            if (post.getBody() != null && !post.getBody().isEmpty()) {
                originalPost.setBody(post.getBody());
            }
            return postsRepository.save(originalPost);
        }
        return null;
    }

    @Override
    public String deletePost(Long id) {
        if (postsRepository.findById(id).isPresent()) {
            postsRepository.deleteById(id);
            return "Post deleted successfully";
        }
        return "No such post in the database";
    }
}
