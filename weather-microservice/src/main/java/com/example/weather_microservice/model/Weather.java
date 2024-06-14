package example.weather_microservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Weather {
    private double temp;
    private double windSpeed;
    private String main;
    private String description;


    @Override
    public String toString() {
        return "Weather{" +
                "temp=" + temp +
                ", windSpeed=" + windSpeed +
                ", main='" + main + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
