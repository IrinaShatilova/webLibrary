package ru.skypro.lessons.springboot.weblibrary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.skypro.lessons.springboot.weblibrary.exception.ApiException;
import ru.skypro.lessons.springboot.weblibrary.message.Message;

@Slf4j
@RestControllerAdvice
public class AdviceController {
    @ExceptionHandler
    public ResponseEntity<Message> handleApiException(ApiException apiException) {
        log.error("Ошибка в апи", apiException);
        return new ResponseEntity<>(new Message(apiException.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Message> handleException(Throwable throwable) {
        log.error("Неизвестная ошибка", throwable);
        return new ResponseEntity<>(new Message("Неизвестная ошибка"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}