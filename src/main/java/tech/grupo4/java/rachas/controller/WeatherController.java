package tech.grupo4.java.rachas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import tech.grupo4.java.rachas.model.openweather.WeatherResponse;
import tech.grupo4.java.rachas.service.WeatherService;

@RestController
@AllArgsConstructor
@RequestMapping("/weather")
public class WeatherController {

    private WeatherService weatherService;

    @GetMapping("/{lat}/{lon}")
    public WeatherResponse getMethodName(@PathVariable Double lat, @PathVariable Double lon) {
        return weatherService.getWeatherInfoByLatAndLon(lat, lon);
    }

}