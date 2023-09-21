package ru.kishko.testTask.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kishko.testTask.entities.City;

public interface CityRepository extends JpaRepository<City, Long> {

}
