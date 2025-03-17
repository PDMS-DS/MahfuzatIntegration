package com.dataserve.mahfuzatintegration.exception;

import java.util.Date;

import com.dataserve.mahfuzatintegration.config.ConfigUtil;
import com.dataserve.mahfuzatintegration.util.ArchiveErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    ConfigUtil configUtil;


    @ExceptionHandler(CustomServiceException.class)
    public ResponseEntity<APIResponseResult<Object>> handleCustomServiceException(CustomServiceException ex) {
        APIResponseResult<Object> response = new APIResponseResult<>(
                null,
                ex.getErrorCode(),
                ex.getLocalizedMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> badCredentialsException(BadCredentialsException ex, WebRequest request) {
        APIResponseResult<Object> response = new APIResponseResult<>(
                null,
                HttpStatus.UNAUTHORIZED.value(),
                configUtil.getLocalMessage("bad.Credential", null)
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
        ErrorResponse errorDetails = new ErrorResponse(new Date(), ex.getMessage(), request.getDescription(false), HttpStatus.OK.value());
        APIResponseResult<Object> response = new APIResponseResult<>(
                null,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<?> serviceException(ServiceException ex, WebRequest request) {
        ErrorResponse errorDetails = new ErrorResponse(new Date(), ex.getMessage(), request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR.value());
        APIResponseResult<Object> response = new APIResponseResult<>(
                null,
                ArchiveErrorCode.BUSINESS.getCode(),
                ex.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        ErrorResponse errorResponse = new ErrorResponse();
//        StringBuilder stringBuilder = new StringBuilder();
//        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
//            if (stringBuilder.length() > 0 )
//                stringBuilder.append(", ");
//            errorResponse.addValidationError(error.getField(), error.getDefaultMessage());
//            stringBuilder.append(error.getDefaultMessage());
//        }
//
//        APIResponseResult<Object> response = new APIResponseResult<>(
//                null,
//                HttpStatus.BAD_REQUEST.value(),
//                stringBuilder.toString()
//        );
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }




    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorResponse errorDetails = new ErrorResponse(new Date(), ex.getMessage(), request.getDescription(false),HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<?> dataNotFoundException(DataNotFoundException ex, WebRequest request) {
        ErrorResponse errorDetails = new ErrorResponse(new Date(), ex.getMessage(), request.getDescription(false), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DataRequiredException.class)
    public ResponseEntity<?> dataRequiredException(DataRequiredException ex, WebRequest request) {
        ErrorResponse errorDetails = new ErrorResponse(new Date(), ex.getMessage(), request.getDescription(false), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }





}
