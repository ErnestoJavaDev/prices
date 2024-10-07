package com.shop.pricing.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.format.DateTimeParseException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingParams(MissingServletRequestParameterException ex) {
        String paramName = ex.getParameterName();
        String errorMessage = String.format("Param '%s' is mandatory", paramName);

        return new ResponseEntity<>(new ErrorResponse(errorMessage, "400", ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String paramName = ex.getName();
        String errorMessage = String.format("Invalid value for param: '%s'", paramName, ex.getValue());

        return new ResponseEntity<>(new ErrorResponse(errorMessage, "400", ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ErrorResponse> handleDateTimeParseException(DateTimeParseException ex) {
        String paramName = ex.getMessage();
        String errorMessage = String.format("Cant parse dateTime as date", paramName, ex.getMessage());

        return new ResponseEntity<>(new ErrorResponse(errorMessage, "400", ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse("Unexpected internal server error", "500", ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
