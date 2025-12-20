package com.vishtech.oms.exception;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class ApiError {
    private Instant timestamp;
    private int status;              // HTTP status
    private String errorCode;         // Business error code (ORD-4001)
    private String message;           // Client-safe message
    private String path;              // API path
    private String traceId;            // Correlation / trace id

    private List<FieldError> errors;  // Optional (for validation)
}
