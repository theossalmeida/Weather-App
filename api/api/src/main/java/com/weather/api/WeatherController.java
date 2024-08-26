package com.weather.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500") // Change this to your front-end URL
public class WeatherController {

    @Value("${openweathermap.api.key}")
    private String apiKey;

    @GetMapping("/weather")
    public ResponseEntity<WeatherResponse> getWeather(@RequestParam String city) {
        String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric", city, apiKey);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);

        // Parse the JSON response
        WeatherResponse response = parseWeatherResponse(result);
        return ResponseEntity.ok(response);
    }

    private WeatherResponse parseWeatherResponse(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(json);

            // Extract data from JSON
            String condition = rootNode.path("weather").get(0).path("main").asText();
            double windSpeed = rootNode.path("wind").path("speed").asDouble();
            int humidity = rootNode.path("main").path("humidity").asInt();
            double tempKelvin = rootNode.path("main").path("temp").asDouble();
            // Convert temperature from Kelvin to Celsius
            double tempCelsius = tempKelvin;

            return new WeatherResponse(condition, windSpeed, humidity, Math.round(tempCelsius * 10.0) / 10.0); // Round to one decimal place
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception as needed (return a default value, error response, etc.)
            return new WeatherResponse("Unknown", 0.0, 0, 0.0);
        }
    }
}
