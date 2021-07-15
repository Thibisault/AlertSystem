package com.SafetyNet.alerts.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FamillyAtAddress {

    private String firstName;
    private String lastName;
    private String age;

    public FamillyAtAddress() {
        super();
    }
}
