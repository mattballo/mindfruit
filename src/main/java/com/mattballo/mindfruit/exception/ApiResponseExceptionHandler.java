package com.mattballo.mindfruit.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mattballo.mindfruit.model.ApiResponse;

import org.apache.tomcat.util.http.fileupload.impl.InvalidContentTypeException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.AuthenticationException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ApiResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleApiException(Exception exception, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (exception instanceof MethodArgumentNotValidException
                || exception instanceof TypeMismatchException
                || exception instanceof JsonProcessingException
                || exception instanceof HttpMessageNotReadableException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (exception instanceof AuthenticationException
                || exception instanceof AccessDeniedException) {
            status = HttpStatus.FORBIDDEN;
        } else if (exception instanceof InvalidContentTypeException) {
            status = HttpStatus.NO_CONTENT;
        } else if (exception instanceof BadCredentialsException
                || exception instanceof DisabledException
                || exception instanceof DataAccessException) {
            status = HttpStatus.UNAUTHORIZED;
        } else if (exception instanceof NotFoundException
                || exception instanceof NoSuchElementException) {
            status = HttpStatus.NOT_FOUND;
        } else if (exception instanceof MethodNotAllowedException
                || exception instanceof HttpRequestMethodNotSupportedException) {
            status = HttpStatus.METHOD_NOT_ALLOWED;
        }

        LoggerFactory.getLogger(ApiResponseExceptionHandler.class).error(exception.getClass().getName());
        return ResponseEntity.status(status).body(new ApiResponse(
                handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(), status, request))
        );
    }

}
