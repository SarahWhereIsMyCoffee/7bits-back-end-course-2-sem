package it.sevenbits.homeworktwo.web.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
    extends ResponseEntityExceptionHandler {
    @ExceptionHandler
            (value = {
            InvalidTaskStatusException.class,
            InvalidTaskTextException.class})
    protected ResponseEntity<Object> invalidStatus(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Invalid task parameters";

        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler
            (value = {InvalidTaskIDException.class})
    protected ResponseEntity<Object> invalidID(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Invalid task ID";

        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler
            (value = {TaskNotFoundException.class})
    protected ResponseEntity<Object> invalidTask(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Task not found";

        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
