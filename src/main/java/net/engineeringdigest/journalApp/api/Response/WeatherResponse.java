package net.engineeringdigest.journalApp.api.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherResponse {
    private Current current;

    @Getter
    @Setter
    public class Current{
        private int temperature;

        @JsonProperty("weather-description")
        private List<String> weatherDescription;
        private int feelsLike;
    }
}
