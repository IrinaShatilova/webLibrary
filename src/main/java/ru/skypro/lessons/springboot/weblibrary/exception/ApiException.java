package ru.skypro.lessons.springboot.weblibrary.exception;

public class ApiException extends RuntimeException{
    public ApiException(String message) {
        super(message);
    }
}
