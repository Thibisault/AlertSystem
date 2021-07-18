package com.SafetyNet.alerts.service;

import com.SafetyNet.alerts.repository.AlertPersistance;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class JsonFile {


    public static AlertPersistance readJsonFile(String nomFichier) {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = TypeReference.class.getResourceAsStream("/" + nomFichier );
        AlertPersistance alertPersistance = null;
        try {
            alertPersistance = mapper.readValue(inputStream, AlertPersistance.class);
            System.out.println("Alert persistance Saved!");
        } catch (IOException e) {
            System.out.println("Unable to save  Alert persistance person: " + e.getMessage());
        }
        return alertPersistance;
    }

    public static void writeJsonFile(String nomFichier, Object object){
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            OutputStream outputStream = new FileOutputStream("src/main/resources/" + nomFichier);
            mapper.writeValue(outputStream, object);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
