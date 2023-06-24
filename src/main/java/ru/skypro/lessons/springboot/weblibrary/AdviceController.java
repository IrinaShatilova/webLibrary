package ru.skypro.lessons.springboot.weblibrary;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.skypro.lessons.springboot.weblibrary.exception.ApiException;
import ru.skypro.lessons.springboot.weblibrary.message.Message;
import ru.skypro.lessons.springboot.weblibrary.service.PositionServiceImpl;


@RestControllerAdvice
public class AdviceController {

    private static final Logger LOG = LoggerFactory.getLogger(AdviceController.class);
    @ExceptionHandler
    public ResponseEntity<Message> handleApiException(ApiException apiException) {
        LOG.error("Ошибка в апи", apiException);
        return new ResponseEntity<>(new Message(apiException.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Message> handleException(Throwable throwable) {
        LOG.error("Неизвестная ошибка", throwable);
        return new ResponseEntity<>(new Message("Неизвестная ошибка"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
