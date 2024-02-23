package com.kirin.demo.exceptions;

/**
 * Ошибка, возникающая при запросе несуществующей заметки
 */
public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException() {
        super("Task not found");
    }

    public TaskNotFoundException(String message) {
        super(message);
    }
}
