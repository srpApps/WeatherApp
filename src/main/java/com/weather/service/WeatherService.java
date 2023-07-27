package com.weather.service;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.weather.model.Weather;

@Service
public class WeatherService {
	
	private static final Log _log = LogFactory.getLog(WeatherService.class);
	
	@Value("${weather.secret.key}")
	private String secretKey;
	
	@Value("${weather.API.host}")
	private String apIhost;
	
	@Value("${weather.error.message}")
	private String errorMsg;
	
	@Value("${weather.api.endpoint}")
	private String apiEndpoint;
	
	public String getWeather(String location) {
		String resp = null;
		
		ObjectMapper mapper = new ObjectMapper();
		
		String url = apiEndpoint+location;
		

		try {
			
			OkHttpClient client = new OkHttpClient();
			Request request = new Request.Builder()
					.url(url)
					.get()
					.addHeader("X-RapidAPI-Key", secretKey)
					.addHeader("X-RapidAPI-Host", apIhost)
					.build();
			
			Response response = client.newCall(request).execute();
			
			String respJson = response.body().string();
			if(response.isSuccessful()) {
				_log.info("success respJson ==>" + respJson);				

				ObjectNode node = mapper.readValue(respJson, ObjectNode.class);
				
				double cur_temp = node.get("current").get("temp_c").asDouble();
				double cur_ftemp = node.get("current").get("feelslike_c").asDouble();
				int humidity = node.get("current").get("humidity").asInt();
				String status = node.get("current").get("condition").get("text").asText();
				String statusImg = node.get("current").get("condition").get("icon").asText();
				String name = node.get("location").get("name").asText();
				
				statusImg = statusImg.replaceFirst("//", "https://");

				Weather w = new Weather(name, status, cur_temp, cur_ftemp, humidity,statusImg);	
				
				resp = mapper.writeValueAsString(w);
			} else {
				_log.info("error ===>" + respJson );
				String message = null;
				ObjectNode node = mapper.readValue(respJson, ObjectNode.class);
				if(node.has("error")) {
					
					message = node.get("error").get("message").asText();
					
				} else {
					
					message = errorMsg;
				}
				resp = mapper.createObjectNode().put("error", message).toString();

			}
			
		} catch (IOException e) {
			_log.error(e.getMessage());
			resp = mapper.createObjectNode().put("error", errorMsg).toString();
		}

		return resp;
	}

}
