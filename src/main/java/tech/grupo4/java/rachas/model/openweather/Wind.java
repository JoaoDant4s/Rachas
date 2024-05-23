package tech.grupo4.java.rachas.model.openweather;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Wind {
    private double speed;
    private int deg;
    private double gust;
}

