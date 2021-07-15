package com.SafetyNet.alerts.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HumanByFirestationNumber {

    private String firestationNumber;
    private String lastName;
    private String phone;
    private String age;
    private String medications[];
    private String allergies[];

    public HumanByFirestationNumber() {
        super();
    }
}
