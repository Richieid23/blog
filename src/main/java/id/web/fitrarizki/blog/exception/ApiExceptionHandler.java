package id.web.fitrarizki.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiExceptionResponse> handleApiException(ApiException e) {
        return ResponseEntity.status(e.getStatus()).body(ApiExceptionResponse.builder().errorMessages(Collections.singletonList(e.getMessage())).build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errorMessages = new ArrayList<>();
        e.getFieldErrors().forEach(error -> errorMessages.add(error.getField() + ": " + error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiExceptionResponse.builder().errorMessages(errorMessages).build());
    }
}
