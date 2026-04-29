package com.UserAuthSystem.utilty;

import lombok.Data;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.mongodb.MongoWriteException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Locale;

@Data
@AllArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({DuplicateKeyException.class, MongoWriteException.class})
    public ResponseEntity<ErrorMessage> duplicateKeyException(Exception ex) {
        String message = "duplicate username found ";
        if (ex instanceof MongoWriteException error
                && error.getError().getCode() == 11000) {
            if (error.getMessage().contains("username")) {
                message = "duplicate username";
            }
        }
        if (ex instanceof DuplicateKeyException err) {
            message = err.getMessage().toLowerCase(Locale.ROOT);
            if (message.contains("username")) {
                message = "user already exist";
            }
        }
        return new ResponseEntity<>(
                new ErrorMessage(
                        message,
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionhandler(Exception ex) {

        System.out.println(Arrays.asList(ex.getStackTrace()));
        return new ResponseEntity<>(
                new ErrorMessage(
                        ex.getMessage(),
                        LocalDateTime.now(),
                        HttpStatus.INTERNAL_SERVER_ERROR),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}