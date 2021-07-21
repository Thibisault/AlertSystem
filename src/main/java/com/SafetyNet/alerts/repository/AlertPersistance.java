package com.SafetyNet.alerts.repository;

import com.SafetyNet.alerts.model.Firestation;
import com.SafetyNet.alerts.model.Medicalrecord;
import com.SafetyNet.alerts.model.Person;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter

public class AlertPersistance {

    private List<Person> persons;
    private List<Firestation> firestations;
    private List<Medicalrecord> medicalrecords;

    public AlertPersistance(){
        super();
    }
}
