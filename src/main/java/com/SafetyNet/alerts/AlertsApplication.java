package com.SafetyNet.alerts;

import com.SafetyNet.alerts.repository.AlertPersistance;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlertsApplication {

	AlertPersistance alertPersistance = new AlertPersistance();


	public static void main(String[] args) {
		SpringApplication.run(AlertsApplication.class, args);
	}
	/*
	private void write(){
		JsonFile.writeJsonFile("C:\\Users\\Thibault\\TestParkings\\alerts\\src\\main\\resources/fichierTest.json", alertPersistance);
	}
	 */
}
