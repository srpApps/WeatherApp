package com.weather.controller;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.weather.model.Weather;
import com.weather.service.WeatherService;

@RestController
@RequestMapping("/weather")
@CrossOrigin
public class WeatherController {

	@Autowired
	private WeatherService service;

	private static final Log _log = LogFactory.getLog(WeatherController.class);

	@GetMapping
	public String home() {
		return "home";
	}

	@RequestMapping(method = RequestMethod.GET, produces = { "application/json" }, path = "/getWeather/{location}")
	public ResponseEntity<String> getWeather(@PathVariable("location") String location) {
		_log.info("Inside getWeather Entered ");
		_log.info("City search ::::  " + location);
		
		String response = service.getWeather(location);
		_log.info("response ====>" + response);
		return ResponseEntity.ok().body(response);
	}

}
