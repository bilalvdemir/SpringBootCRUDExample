package com.bilald.crudsample.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private String errorCode;
    private String message;
    private Instant timestamp;
    private String path;
    private String traceId; // For distributed tracing

    // Validation errors
    private Map<String, String> fieldErrors;

    // Additional details
    private List<String> details;

    public static ErrorResponse of(String errorCode, String message, String path) {
        return ErrorResponse.builder()
                .errorCode(errorCode)
                .message(message)
                .timestamp(Instant.now())
                .path(path)
                .build();
    }
}
