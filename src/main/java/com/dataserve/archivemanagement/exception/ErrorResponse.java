package com.dataserve.archivemanagement.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private Date timestamp;
    private String message;
    private String details;
    private Integer statusCode;
    private List<ValidationError> validationErrors;

    public ErrorResponse(Date timestamp, String message, String details, Integer statusCode) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.statusCode = statusCode;

    }

    public void addValidationError(String field, String message) {
        if (Objects.isNull(validationErrors)) {
            validationErrors = new ArrayList<>();
        }
        validationErrors.add(new ValidationError(field, message));
    }
}




