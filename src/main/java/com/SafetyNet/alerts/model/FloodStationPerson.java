package com.SafetyNet.alerts.model;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
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
