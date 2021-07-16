package com.SafetyNet.alerts;

import com.SafetyNet.alerts.service.AlertService;
import com.SafetyNet.alerts.service.JsonFile;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlertsApplication {


	static JsonFile jsonFile = new JsonFile();

	public static void main(String[] args) {
		SpringApplication.run(AlertsApplication.class, args);
		jsonFile.readJsonFile();
	}
}
