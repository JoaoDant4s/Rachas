package tech.grupo4.java.rachas.model.openweather;

import java.util.List;

public record CurrentWeather(long dt, long sunrise, long sunset, double temp, double feels_like,
        int pressure, int humidity, double dew_point, double uvi, int clouds,
        int visibility, double wind_speed, int wind_deg, double wind_gust,
        List<Weather> weather) {
}
