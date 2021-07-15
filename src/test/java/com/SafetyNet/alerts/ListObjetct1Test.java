package com.SafetyNet.alerts;

import com.SafetyNet.alerts.model.Firestation;
import com.SafetyNet.alerts.model.Medicalrecord;
import com.SafetyNet.alerts.model.Person;
import com.SafetyNet.alerts.repository.AlertPersistance;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Component
public class ListObjetct1Test {

    List<List<Person>> personList = new ArrayList<List<Person>>();
    List<Firestation> firestationList = new ArrayList<>();
    List<Medicalrecord> medicalrecordList = new ArrayList<>();

    AlertPersistance alertPersistance = new AlertPersistance();

    public List<List<Person>> remplirListePeronne() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = TypeReference.class.getResourceAsStream("/fichierInitial.json");
        alertPersistance = mapper.readValue(inputStream, AlertPersistance.class);
        personList.add(alertPersistance.getPersons());
        return personList;
    }

}
