package com.SafetyNet.alerts.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PersonByFirestationNumber {

    private String firstName;
    private String lastName;
    private String address;
    private String phone;

    public PersonByFirestationNumber() {
        super();
    }
}
