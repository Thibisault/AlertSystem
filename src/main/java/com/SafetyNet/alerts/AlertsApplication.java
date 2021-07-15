package com.SafetyNet.alerts;

import com.SafetyNet.alerts.service.AlertService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlertsApplication {

	private static AlertService alertService = new AlertService();


	public static void main(String[] args) {
		SpringApplication.run(AlertsApplication.class, args);
		alertService.readJsonFile("/fichierInitial.json");
	}
}
