package ru.skypro.lessons.springboot.weblibrary;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.skypro.lessons.springboot.weblibrary.exception.ApiException;
import ru.skypro.lessons.springboot.weblibrary.pojo.Message;

@RestControllerAdvice
public class AdviceController {
    @ExceptionHandler
    public ResponseEntity<Message> handleApiException(ApiException apiException) {
        return new ResponseEntity<>(new Message(apiException.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Message> handleException(Throwable throwable) {
        return new ResponseEntity<>(new Message("Неизвестная ошибка"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
