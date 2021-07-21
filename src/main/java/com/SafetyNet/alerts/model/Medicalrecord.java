package com.SafetyNet.alerts.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
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
