package ru.kishko.testTask.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CityExceptionHandler {

    @ExceptionHandler(value = {CityNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFoundException(CityNotFoundException cityNotFoundException) {

        CityException cityException = new CityException(
                cityNotFoundException.getMessage(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(cityException, cityException.getHttpStatus());

    }

}
