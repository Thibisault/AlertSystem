package com.SafetyNet.alerts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.catalina.LifecycleState;

import java.util.List;

@Data
@AllArgsConstructor
public class Medicalrecord {

    private String firstName;
    private String lastName;
    private String birthdate;

    private String[] medications;
    private String[] allergies;

    public Medicalrecord(){
        super();
    }
}
