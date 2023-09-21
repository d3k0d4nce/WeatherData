package ru.kishko.testTask.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kishko.testTask.entities.City;
import ru.kishko.testTask.errors.CityNotFoundException;
import ru.kishko.testTask.repositories.CityRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public City getCityById(Long cityId) {
        return cityRepository.findById(cityId).orElseThrow(
                () -> new CityNotFoundException("There is no city with id: " + cityId)
        );
    }
}
