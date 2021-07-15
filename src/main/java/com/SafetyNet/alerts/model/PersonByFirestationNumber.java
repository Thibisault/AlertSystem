package com.SafetyNet.alerts.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonByFirestationNumber {

    private String firstName;
    private String lastName;
    private String address;
    private String phone;

    public PersonByFirestationNumber() {
        super();
    }
}
