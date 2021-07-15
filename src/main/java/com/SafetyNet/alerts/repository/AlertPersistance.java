package com.SafetyNet.alerts.repository;

import com.SafetyNet.alerts.model.Firestation;
import com.SafetyNet.alerts.model.Medicalrecord;
import com.SafetyNet.alerts.model.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@Component
public class AlertPersistance {

    private List<Person> persons;
    private List<Firestation> firestations;
    private List<Medicalrecord> medicalrecords;

    public AlertPersistance(){
        super();
    }
}
