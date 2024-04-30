package tech.grupo4.java.rachas.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tech.grupo4.java.rachas.model.openweather.WeatherResponse;

@FeignClient(value = "openWeather", url = "${open.weather.url}")
public interface WeatherRepository {
    
    @GetMapping
    WeatherResponse getWeatherInfoByLatAndLon(
        @RequestParam("lat") double latitude,
        @RequestParam("lon") double longitude,
        @RequestParam("appid") String apiKey
    );
}
