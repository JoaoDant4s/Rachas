package tech.grupo4.java.rachas.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tech.grupo4.java.rachas.model.openweather.WeatherResponse;
import tech.grupo4.java.rachas.repository.WeatherRepository;

@Service
@RequiredArgsConstructor
public class WeatherService {

    @Value("${open.weather.api.key}")
    private String apiKey;
    private final WeatherRepository weatherRepository;

    public WeatherResponse getWeatherInfoByLatAndLon(double lat, double lon) {
        return weatherRepository.getWeatherInfoByLatAndLon(lat, lon, apiKey);
    }
}
