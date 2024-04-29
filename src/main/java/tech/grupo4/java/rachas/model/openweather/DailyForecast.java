package tech.grupo4.java.rachas.model.openweather;

import java.util.List;

public record DailyForecast(long dt, long sunrise, long sunset, long moonrise, long moonset,
double moon_phase, String summary, Temp temp, FeelsLike feels_like,
int pressure, int humidity, double dew_point, double wind_speed,
int wind_deg, double wind_gust, List<Weather> weather, int clouds,
double pop, double rain, double uvi) {}
