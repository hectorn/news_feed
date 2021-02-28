package com.task.news.feed.gateway.rest.middleware;

import com.task.news.feed.domain.exception.DomainException;
import com.task.news.feed.infrastructure.exception.InfrastructureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionMiddleware {

    @ExceptionHandler(DomainException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleException(DomainException exception) {
        return new ResponseEntity<>(ErrorResponse.of(exception.getCode(), exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InfrastructureException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleException(InfrastructureException exception) {
        return new ResponseEntity<>(ErrorResponse.of(exception.getCode(), exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        System.out.println(exception.getClass());
        return new ResponseEntity<>(ErrorResponse.of("INTERNAL_SERVER_ERROR", exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
