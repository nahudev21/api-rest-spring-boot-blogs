package com.Nahudev.application_blog_api_rest.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BlogAppException extends RuntimeException{

    private static final Long serialVersionUID = 1L;

    private HttpStatus state;

    private String message;

    public BlogAppException(HttpStatus state, String message) {
        super();
        this.state = state;
        this.message = message;
    }

    public BlogAppException(HttpStatus state, String message, String message1) {
        super();
        this.state = state;
        this.message = message;
        this.message = message1;
    }
}
