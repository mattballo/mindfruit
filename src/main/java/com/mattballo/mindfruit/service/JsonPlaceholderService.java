package com.mattballo.mindfruit.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mattballo.mindfruit.entity.Post;
import com.mattballo.mindfruit.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@Service
public class JsonPlaceholderService implements IJsonPlaceholderService {

    @Value("${json-placeholder.api.url.users}")
    private String usersApiUrl;

    @Value("${json-placeholder.api.url.posts}")
    private String postsApiUrl;

    @Override
    public Optional<List<User>> getAllUsers() {
        try {
            HttpURLConnection conn = getConnection(usersApiUrl);
            ObjectMapper mapper = new ObjectMapper();
            TypeFactory typeFactory = mapper.getTypeFactory();
            return Optional.of(mapper.readValue(
                    conn.getInputStream(),
                    typeFactory.constructCollectionType(List.class, User.class)
            ));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public Optional<User> getUser(Long id) {
        try {
            HttpURLConnection conn = getConnection(usersApiUrl + id);
            ObjectMapper mapper = new ObjectMapper();
            return Optional.of(mapper.readValue(conn.getInputStream(), User.class));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        try {
            HttpURLConnection conn = getConnection(usersApiUrl + "?email=" + email);
            ObjectMapper mapper = new ObjectMapper();
            return Optional.of(mapper.readValue(conn.getInputStream(), User.class));
        } catch (IOException e) {
            return Optional.empty();
        }
    }


    @Override
    public Optional<Post> getPost(Long id) {
        try {
            HttpURLConnection conn = getConnection(postsApiUrl + id);
            ObjectMapper mapper = new ObjectMapper();
            return Optional.of(mapper.readValue(conn.getInputStream(), Post.class));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    private HttpURLConnection getConnection(String url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("GET");
        return conn;
    }

}
