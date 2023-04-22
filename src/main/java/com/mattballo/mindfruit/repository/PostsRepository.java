package com.mattballo.mindfruit.repository;

import com.mattballo.mindfruit.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<Post, Long> {

    List<Post> findByUserId(Long id);

    @Query(value = "SELECT coalesce(max(id), 0) + 1 FROM post", nativeQuery = true)
    Long getNextId();

}
