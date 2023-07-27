package com.weather.model;

import java.io.Serializable;

public class Weather implements Serializable {

	private String city;
	private String status;
	private double temperature;
	private double feelsTemperature;
	private int humidity;
	private String statusImg;
	
	public Weather() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Weather(String city, String status, double temperature, double feelsTemperature, int humidity,String statusImg) {
	
		this.city = city;
		this.status = status;
		this.temperature = temperature;
		this.feelsTemperature = feelsTemperature;
		this.humidity = humidity;
		this.statusImg = statusImg;
	}
	
	
	


	public String getStatusImg() {
		return statusImg;
	}


	public void setStatusImg(String statusImg) {
		this.statusImg = statusImg;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public double getTemperature() {
		return temperature;
	}


	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}


	public double getFeelsTemperature() {
		return feelsTemperature;
	}


	public void setFeelsTemperature(double feelsTemperature) {
		this.feelsTemperature = feelsTemperature;
	}


	public int getHumidity() {
		return humidity;
	}


	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}


	@Override
	public String toString() {
		return "{city=" + city + ", status=" + status + ", temperature=" + temperature + ", feelsTemperature="
				+ feelsTemperature + ", humidity=" + humidity + "}";
	}
	
	
}
