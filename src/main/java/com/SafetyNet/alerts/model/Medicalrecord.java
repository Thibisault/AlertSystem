package com.SafetyNet.alerts.model;

import lombok.AllArgsConstructor;
import lombok.Data;


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
