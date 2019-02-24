package es.uv.dbcds.comments.commentsapi.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * MessageNotFoundException
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MessageNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public MessageNotFoundException() {
        this("Resource not found!");
    }

    public MessageNotFoundException(String message) {
        this(message, null);
    }

    
    public MessageNotFoundException(String message,Throwable cause) {
        super(message, cause);
    }

}