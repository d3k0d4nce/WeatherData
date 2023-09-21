package ru.kishko.testTask.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class CityException {

    private final String message;

    private final HttpStatus httpStatus;

}
