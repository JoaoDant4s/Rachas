package tech.grupo4.java.rachas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.grupo4.java.rachas.model.openweather.WeatherResponse;
import tech.grupo4.java.rachas.repository.WeatherRepository;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private WeatherRepository weatherRepository;

    public WeatherController(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @GetMapping("/{lat}/{lon}")
    public WeatherResponse getMethodName(@PathVariable Double lat, @PathVariable Double lon) {
        return weatherRepository.getWeatherInfoByLatAndLon(lat, lon, "9d51ea2323133f48ed19fee08e76a213");
    }

}