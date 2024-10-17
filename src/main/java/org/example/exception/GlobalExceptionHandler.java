package org.example.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler({RestClientException.class})
    public ResponseEntity<String> handleRestError(RestClientException e) {
        log.error("Error while getting data from OpenWeather API, exception is {}", e.getMessage());
        if (e instanceof HttpClientErrorException exception) {
            return ResponseEntity.status(exception.getStatusCode()).body(exception.getResponseBodyAsString());
        } else {
            return ResponseEntity.internalServerError().body("Sorry, we're unable to fetch weather data at this time.");
        }
    }

    @ExceptionHandler({JsonProcessingException.class})
    public ResponseEntity<String> handleJsonError(JsonProcessingException e) {
        log.error("Error while mapping data from OpenWeather API to Dynamo entity, exception is {}", e.getMessage());
        return ResponseEntity.internalServerError().body("Sorry, something went wrong while parsing weather data. Please try later");
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleDynamoDBException(Exception e) {
        if (e instanceof AmazonDynamoDBException ade) {
            log.error("Error while saving data to Dynamo DB, error is {}", ade.getMessage());
            return ResponseEntity.badRequest().body(ade.getErrorMessage());
        } else {
            log.error("Error while requesting/processing weather data {}", e.getMessage());
            return ResponseEntity.internalServerError().body("Something went wrong and we're working on it. We regret the inconvenience.");
        }
    }
}
