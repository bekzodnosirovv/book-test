package book.store.hadler;

import book.store.exp.AppBadRequestException;
import book.store.exp.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<String> handler(UserNotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(AppBadRequestException.class)
    private ResponseEntity<String> handler(AppBadRequestException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
