package ru.kishko.testTask.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kishko.testTask.entities.WeatherData;
import ru.kishko.testTask.services.WeatherDataService;

import java.io.IOException;

@RestController
@RequestMapping("/weatherData")
@RequiredArgsConstructor
public class WeatherDataController {

    private final WeatherDataService weatherDataService;

    @GetMapping
    public ResponseEntity<String> collectWeatherData() throws JsonProcessingException {
        weatherDataService.collectWeatherData();
        return new ResponseEntity<>("Successfully updated", HttpStatus.OK);
    }

}
