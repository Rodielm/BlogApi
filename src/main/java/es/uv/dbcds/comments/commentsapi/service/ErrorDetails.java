package es.uv.dbcds.comments.commentsapi.service;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * ErrorDetails
 */
@Getter
@Setter
public class ErrorDetails {
    private String errorCode;
    private String errorMessage;
    private String devErrorMessage;
    private Map<String, Object> additionalData = new HashMap<>();
}