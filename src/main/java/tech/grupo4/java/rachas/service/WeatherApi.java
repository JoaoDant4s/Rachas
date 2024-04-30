import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.RequestBody;

public interface WeatherApi {

    @RequestLine("GET /data/2.5/weather?q={city}&appid={apiKey}")
    WeatherResponse getWeatherByCity(@Param("city") String city, @Param("apiKey") String apiKey);

    @RequestLine("GET /data/2.5/weather?lat={lat}&lon={lon}&appid={apiKey}")
    WeatherResponse getWeatherByCoordinates(@Param("lat") Double lat, @Param("lon") Double lon, @Param("apiKey") String apiKey);

    class WeatherResponse {
        private Main main;
        private List<Weather> weather;


        public static class Main {
            private Double temp;
            private Integer pressure;
            private Integer humidity;

        }

        public static class Weather {
            private String description;

        }
    }
}