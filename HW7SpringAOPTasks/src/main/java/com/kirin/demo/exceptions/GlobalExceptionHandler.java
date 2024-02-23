package com.kirin.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Обработчик исключений
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Обработка исключения IncorrectDataException (невалидные данные)
     */
    @ExceptionHandler(IncorrectDataException.class)
    public ResponseEntity<?> handleIncorrectDataException(
            IncorrectDataException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    /**
     * Обработка исключения TaskNotFoundException (задача не найдена)
     */
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<?> handleTaskNotFoundException(
            TaskNotFoundException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
}
