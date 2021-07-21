package com.SafetyNet.alerts.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class FloodStationPerson {

    private String lastName;
    private String phone;
    private String age;
    private String[] medications;
    private String[] allergies;


    public FloodStationPerson() {
        super();
    }
}
