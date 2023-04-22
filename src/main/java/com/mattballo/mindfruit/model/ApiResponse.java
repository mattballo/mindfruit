package com.mattballo.mindfruit.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@Schema(hidden = true)
public class ApiResponse<T> {

    private T data;
    private ResponseEntity errorResponse = null;

    @JsonCreator
    public ApiResponse(@JsonProperty("data") T data) {
        this.data = data;
    }

    @JsonCreator
    public ApiResponse(@JsonProperty("data") T data, @JsonProperty("error") ResponseEntity errorResponse) {
        this.data = data;
        this.errorResponse = errorResponse;
    }

    @JsonCreator
    public ApiResponse(@JsonProperty("error") ResponseEntity errorResponse) {
        this.data = null;
        this.errorResponse = errorResponse;
    }

}