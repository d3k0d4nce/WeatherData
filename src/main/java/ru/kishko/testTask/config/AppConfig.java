package ru.kishko.testTask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration(value = "myConfig")
public class AppConfig {

    @Bean
    public WebClient webCityClient() {
        return WebClient.builder().baseUrl("https://api.openweathermap.org/data/2.5/weather").build();
    }

    @Bean
    public String key() {
        return "&appid=00cef900d912232cd413dfe728e89310";
    }

}
