package org.example.service;

import java.util.Map;
import org.example.model.WeatherData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class WeatherService {
	
	@Autowired
	private DynamoDBMapper dynamoDBMapper;
	
	@Autowired
    private RestTemplate restTemplate;
	
    @Value("${open.weather.uri}")
    private String weatherUri;
    
    private static final Logger log = LoggerFactory.getLogger(WeatherService.class);
    
    public WeatherData getWeatherOfCity(String city) throws JsonProcessingException {
        Map<String, String> params = Map.of("city", city);
        log.info("Sending request to OpenWeather API with params {}", city);
        WeatherData weatherData = (WeatherData)this.restTemplate.getForObject(this.weatherUri, WeatherData.class, params);
        this.dynamoDBMapper.save(weatherData);
        log.info("Successfully saved data in Dynamo DB for id {}", weatherData.getId());
        return weatherData;
    }
    
    public String deleteById(String id){
        dynamoDBMapper.delete(id);
        return "Data deleted successfully...!";

    }
}
