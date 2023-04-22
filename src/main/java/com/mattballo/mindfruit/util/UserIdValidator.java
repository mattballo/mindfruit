package com.mattballo.mindfruit.util;

import com.mattballo.mindfruit.annotation.ValidUserId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class UserIdValidator implements ConstraintValidator<ValidUserId, Long> {

    @Override
    public boolean isValid(Long userId, ConstraintValidatorContext context) {
        if (userId == null) {
            return true;
        }

        boolean isValid = isUserIdValid(userId);
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Invalid external user ID").addConstraintViolation();
        }
        return isValid;
    }

    private boolean isUserIdValid(Long userId) {
        String EXTERNAL_API_URL = "https://jsonplaceholder.typicode.com/users/";
        try {
            URL url = new URL(EXTERNAL_API_URL + userId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            return conn.getResponseCode() == HttpStatus.OK.value();
        } catch (IOException e) {
            return false;
        }
    }

}
