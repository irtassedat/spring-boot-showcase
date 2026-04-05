package com.workintech.s19d1.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ExceptionResponse {
    private String message;
    private int status;
    private LocalDateTime dateTime;
}