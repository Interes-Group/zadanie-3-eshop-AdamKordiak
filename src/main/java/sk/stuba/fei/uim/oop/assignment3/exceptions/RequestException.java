package sk.stuba.fei.uim.oop.assignment3.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RequestException extends Throwable {
    @ExceptionHandler(value = {RequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleException(){
    }
}
