package tech.grupo4.java.rachas.model.openweather;

import java.util.List;

public record WeatherResponse(
        double lat,
        double lon,
        String timezone,
        int timezone_offset,
        CurrentWeather current,
        List<MinuteForecast> minutely,
        List<HourlyForecast> hourly,
        List<DailyForecast> daily,
        List<Alert> alerts) {
}
