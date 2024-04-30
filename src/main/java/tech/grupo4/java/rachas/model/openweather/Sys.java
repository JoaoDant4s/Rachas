package tech.grupo4.java.rachas.model.openweather;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Sys {
    private int type;
    private int id;
    private String country;
    private long sunrise;
    private long sunset;
}
