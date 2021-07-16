package com.SafetyNet.alerts.service;

import com.SafetyNet.alerts.repository.AlertPersistance;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class JsonFile {

    AlertService alertService = new AlertService();

    public void readJsonFile() {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = TypeReference.class.getResourceAsStream("/fichierInitial.json");
        try {
            AlertPersistance alertPersistance = mapper.readValue(inputStream, AlertPersistance.class);
            alertService.saveAlertPersistance(alertPersistance);
            System.out.println("Alert persistance Saved!");
        } catch (IOException e) {
            System.out.println("Unable to save  Alert persistance person: " + e.getMessage());
        }
    }

    public static void writeJsonFile(String cheminDuFichierACreer, Object object){
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            mapper.writeValue(new File(cheminDuFichierACreer), object);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
