package tech.grupo4.java.rachas.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tech.grupo4.java.rachas.model.openweather.WeatherResponse;

@FeignClient(value = "openWeather")
public interface WeatherRepository {
    
    @GetMapping
    WeatherResponse getAll(
        @RequestParam("lat") double latitude,
        @RequestParam("lon") double longitude,
        @RequestParam("appid") String apiKey
    );
}
