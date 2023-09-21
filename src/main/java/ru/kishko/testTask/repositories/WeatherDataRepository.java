package ru.kishko.testTask.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kishko.testTask.entities.City;
import ru.kishko.testTask.entities.WeatherData;

public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {

    WeatherData findWeatherDataByCity(City city);

}
