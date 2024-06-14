package example.weather_microservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import example.weather_microservice.model.Location;
import example.weather_microservice.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class WeatherService {
    @Autowired
    public RestTemplate restTemplate;
    private final String API_KEY = "0c072284079a302d6d11ad45aaddc75c";
    public Map<String, Object> jsonToMap(String str) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<Map<String, Object>> typeReference = new TypeReference<>() {
        };
        Map<String, Object> map = objectMapper.readValue(str, typeReference);
        return map;
    }
    public Weather weather(@PathVariable int locId){
        Location location = restTemplate.getForObject("http://localhost:8082/location/"+locId,Location.class);
        Weather nowWeather = new Weather();
        assert location != null;
        String lat = location.getLatitude();
        String lon = location.getLongitude();
        String urlString = "https://api.openweathermap.org/data/2.5/weather?lon=" + lon + "&lat=" + lat
                +"&units=metric&appid=" + API_KEY;
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();

            Map<String, Object> respMap = jsonToMap(result.toString());

            Map<String, Object> weatherMain = (Map<String, Object>) respMap.get("main");
            ArrayList<Map<String, Object>> weathers = (ArrayList<Map<String, java.lang.Object>>) respMap.get("weather");
            LinkedHashMap<Map<String, Object>, Object> weatherWind = (LinkedHashMap<Map<String, Object>, Object>) respMap.get("wind");

            nowWeather.setTemp((Double) weatherMain.get("temp"));
            nowWeather.setWindSpeed(Double.parseDouble(weatherWind.get("speed").toString()));
            nowWeather.setDescription((String) weathers.get(0).get("description"));
            nowWeather.setMain((String) weathers.get(0).get("main"));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return nowWeather;
    }
}
