package org.example.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedJson;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@DynamoDBTable(tableName = "WeatherData")
public class WeatherData {
	
	@DynamoDBHashKey(attributeName = "id")
    @DynamoDBAttribute
    private String id;
	
    @DynamoDBAttribute
    @DynamoDBTypeConvertedJson
    private String base;
    
    @DynamoDBAttribute
    @DynamoDBTypeConvertedJson
    private Clouds clouds;
    
    @DynamoDBTypeConvertedJson
    @DynamoDBAttribute
    private int cod;
    
    @DynamoDBTypeConvertedJson
    @DynamoDBAttribute
    private Coord coord;
    
    @DynamoDBTypeConvertedJson
    @DynamoDBAttribute
    private long dt;
    
    @DynamoDBTypeConvertedJson
    @DynamoDBAttribute
    private Main main;
    
    @DynamoDBTypeConvertedJson
    @DynamoDBAttribute
    private String name;
    
    @DynamoDBTypeConvertedJson
    @DynamoDBAttribute
    private rain rain;
    
    @DynamoDBTypeConvertedJson
    @DynamoDBAttribute
    private Sys sys;
    
    @DynamoDBTypeConvertedJson
    @DynamoDBAttribute
    private int timezone;
    
    @DynamoDBTypeConvertedJson
    @DynamoDBAttribute
    private int visibility;
    
    @DynamoDBTypeConvertedJson
    @DynamoDBAttribute
    private Weather[] weather;
    
    @DynamoDBTypeConvertedJson
    @DynamoDBAttribute
    private Wind wind;

    // Getters and setters for all fields

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Clouds {
        private int all;

        // Getter and setter
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Coord {
        private double lat;
        private double lon;

        // Getters and setters
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Main {
        private double feels_like;
        private int grnd_level;
        private int humidity;
        private int pressure;
        private int sea_level;
        private double temp;
        private double temp_max;
        private double temp_min;

        // Getters and setters
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class rain {
        private double h1;

        // Getter and setter
        public double getH1() {
            return h1;
        }

        public void setH1(double h1) {
            this.h1 = h1;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Sys {
        private String country;
        private int id;
        private long sunrise;
        private long sunset;
        private int type;

        // Getters and setters
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Weather {
        private String description;
        private String icon;
        private int id;
        private String main;

        // Getters and setters
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Wind {
        private int deg;
        private double speed;
        private double gust;

        // Getters and setters
    }

}
