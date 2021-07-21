package com.SafetyNet.alerts.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
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
