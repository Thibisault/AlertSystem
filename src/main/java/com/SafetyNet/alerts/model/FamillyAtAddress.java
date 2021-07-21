package com.SafetyNet.alerts.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class FamillyAtAddress {

    private String firstName;
    private String lastName;
    private String age;

    public FamillyAtAddress() {
        super();
    }
}
