package book.store.hadler;

import book.store.exp.AppBadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(AppBadRequestException.class)
    private ResponseEntity<String> handler(AppBadRequestException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
