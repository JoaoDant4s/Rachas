import feign.Feign;
import feign.jackson.JacksonDecoder;
import org.springframework.stereotype.Component;

@Component
public class WeatherFeignClient {

    private final WeatherApi weatherApi;

    public WeatherFeignClient(WeatherApi.WeatherResponseDecoder decoder) {
        this.weatherApi = Feign.builder()
                .decoder(decoder)
                .target(WeatherApi.class, "https://api.openweathermap.org");
    }

    public WeatherApi.WeatherResponse getWeatherByCity(String city) {
        return weatherApi.getWeatherByCity(city, "y8bdfe793c163fddd95d836188d6340a2");
    }

    public WeatherApi.WeatherResponse getWeatherByCoordinates(Double lat, Double lon) {
        return weatherApi.getWeatherByCoordinates(lat, lon, "8bdfe793c163fddd95d836188d6340a2");
    }

    public static class WeatherResponseDecoder extends JacksonDecoder {
        public WeatherResponseDecoder() {
            super(new ObjectMapper());
        }
    }
}