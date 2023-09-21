package ru.kishko.testTask.services;

import ru.kishko.testTask.entities.City;

import java.util.List;

public interface CityService {
    List<City> getAllCities();

    City getCityById(Long cityId);
}
