package ru.skypro.lessons.springboot.weblibrary.exception;

import java.io.IOException;

public class ApiException extends RuntimeException{
    public ApiException(String message) {
        super(message);
    }


}
