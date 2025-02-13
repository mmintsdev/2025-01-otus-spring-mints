package ru.otus.hw.exceptions;

public class NoQuestionsAvailableException extends RuntimeException {
    public NoQuestionsAvailableException(String message) {
        super(message);
    }
}