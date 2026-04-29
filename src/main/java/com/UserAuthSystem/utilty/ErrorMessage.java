package com.UserAuthSystem.utilty;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorMessage {
    private String message;
    private LocalDateTime time;
    private HttpStatus statusCode;
}
