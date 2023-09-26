package ru.kishko.testTask.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.kishko.testTask.entities.City;
import ru.kishko.testTask.entities.WeatherData;
import ru.kishko.testTask.repositories.CityRepository;
import ru.kishko.testTask.repositories.WeatherDataRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class WeatherDataServiceImpl implements WeatherDataService {

    private final CityRepository cityRepository;
    private final WebClient webCityClient;
    private final WeatherDataRepository weatherDataRepository;
    private final String key;

    @Override
    @Scheduled(cron = "0 0 * * * *")
    public void collectWeatherData() throws JsonProcessingException {
        List<City> cities = cityRepository.findAll();
        for (City city : cities) {

            if (weatherDataRepository.findWeatherDataByCity(city) == null) {
                weatherDataRepository.save(getWeatherData(city));
                continue;
            }

            updateWeatherData(getWeatherData(city));
        }

    }

    @Override
    public WeatherData updateWeatherData(WeatherData weatherData) {

        WeatherData weatherDataDB = weatherDataRepository.findWeatherDataByCity(weatherData.getCity());

        String weatherDescription = weatherData.getWeatherDescription();
        Double humidity = weatherData.getHumidity();
        Double pressure = weatherData.getPressure();
        Double temperature = weatherData.getTemperature();
        Integer windDirection = weatherData.getWindDirection();
        Double windSpeed = weatherData.getWindSpeed();
        Integer visibility = weatherData.getVisibility();

        if (Objects.nonNull(weatherDescription) && !"".equalsIgnoreCase(weatherDescription)) {
            weatherDataDB.setWeatherDescription(weatherDescription);
        }

        if (Objects.nonNull(humidity) && humidity >= 0) {
            weatherDataDB.setHumidity(humidity);
        }

        if (Objects.nonNull(pressure) && pressure >= 0) {
            weatherDataDB.setPressure(pressure);
        }

        if (Objects.nonNull(temperature) && temperature >= 0) {
            weatherDataDB.setTemperature(temperature);
        }

        if (Objects.nonNull(windDirection) && windDirection >= 0) {
            weatherDataDB.setWindDirection(windDirection);
        }

        if (Objects.nonNull(windSpeed) && windSpeed >= 0) {
            weatherDataDB.setWindSpeed(windSpeed);
        }

        if (Objects.nonNull(visibility) && visibility >= 0) {
            weatherDataDB.setVisibility(visibility);
        }

        weatherDataRepository.save(weatherDataDB);

        return weatherDataDB;
    }


    private WeatherData getWeatherData(City city) throws JsonProcessingException {

        String weatherData = webCityClient.get().uri("?q=" + city.getName() + key).retrieve().bodyToMono(String.class).block();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(weatherData);

        return WeatherData.builder()
                .city(city)
                .weatherDescription(jsonNode.get("weather").get(0).get("description").asText())
                .humidity(jsonNode.get("main").get("humidity").asDouble())
                .pressure(jsonNode.get("main").get("pressure").asDouble())
                .temperature(jsonNode.get("main").get("temp").asDouble())
                .windDirection(jsonNode.get("wind").get("deg").asInt())
                .windSpeed(jsonNode.get("wind").get("speed").asDouble())
                .visibility(jsonNode.get("visibility").asInt())
                .build();
    }

}
