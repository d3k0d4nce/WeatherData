package ru.kishko.testTask.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.kishko.testTask.entities.WeatherData;

public interface WeatherDataService {
    void collectWeatherData() throws JsonProcessingException;

    void updateWeatherData(WeatherData weatherData);
}
