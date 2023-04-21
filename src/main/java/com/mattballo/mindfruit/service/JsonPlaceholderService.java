package com.mattballo.mindfruit.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.mattballo.mindfruit.entity.Post;
import com.mattballo.mindfruit.entity.User;
import com.mattballo.mindfruit.util.ExternalApiUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

@Service
public class JsonPlaceholderService implements IJsonPlaceholderService {

    @Value("${json-placeholder.api.url.users}")
    private String usersApiUrl;

    @Value("${json-placeholder.api.url.posts}")
    private String postsApiUrl;

    @Override
    public List<User> getAllUsers() {
        try {
            HttpURLConnection conn = ExternalApiUtil.getConnection(usersApiUrl);
            ObjectMapper mapper = new ObjectMapper();
            TypeFactory typeFactory = mapper.getTypeFactory();
            return mapper.readValue(
                    conn.getInputStream(),
                    typeFactory.constructCollectionType(List.class, User.class)
            );
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public User getUser(Long id) {
        try {
            HttpURLConnection conn = ExternalApiUtil.getConnection(usersApiUrl + id);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(conn.getInputStream(), User.class);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public User getUserByEmail(String email) {
        try {
            HttpURLConnection conn = ExternalApiUtil.getConnection(usersApiUrl + "?email=" + email);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(conn.getInputStream(), User.class);
        } catch (IOException e) {
            return null;
        }
    }


    @Override
    public Post getPost(Long id) {
        try {
            HttpURLConnection conn = ExternalApiUtil.getConnection(postsApiUrl + id);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(conn.getInputStream(), Post.class);
        } catch (IOException e) {
            return null;
        }
    }

}
