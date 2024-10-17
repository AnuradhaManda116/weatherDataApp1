package org.example.controller;

import org.example.model.WeatherData;
import org.example.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/weather")
public class WeatherController {
	
	@Autowired
	private WeatherService weatherService;
	
	 @GetMapping("/{city}")
	 public WeatherData getWeather(@PathVariable String city) throws JsonProcessingException{
		 return this.weatherService.getWeatherOfCity(city);
	 }
	 
     @DeleteMapping("/{id}")
     public String deleteCityById(@PathVariable String city){
         weatherService.deleteById(city);
         return "Data deleted...!";
     }

}
