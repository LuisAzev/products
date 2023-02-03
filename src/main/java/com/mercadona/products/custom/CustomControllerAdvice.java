package com.mercadona.products.custom;

import com.mercadona.products.custom.exception.EntityNotFoundException;
import com.mercadona.products.custom.exception.InvalidEanCodeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class CustomControllerAdvice
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleCustomJpaNotFound(Exception ex) {

        log.warn( ex.toString() );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(InvalidEanCodeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleCustomInvalidEanCode(Exception ex) {

        log.warn( ex.toString() );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
