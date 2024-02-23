package com.kirin.demo.exceptions;

/**
 * Ошибка, возникающая при передаче невалидных данных
 */
public class IncorrectDataException extends RuntimeException {

    public IncorrectDataException(String message) {
        super(message);
    }

    public IncorrectDataException() {
        super("Incorrect data");
    }
}
