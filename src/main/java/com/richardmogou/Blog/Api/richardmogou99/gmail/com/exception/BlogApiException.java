package com.richardmogou.Blog.Api.richardmogou99.gmail.com.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class BlogApiException extends RuntimeException {
    @Getter
    private HttpStatus httpStatus;
    private String message;

    public BlogApiException(String message, HttpStatus httpStatus, String message1) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message1;
    }

    public BlogApiException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
