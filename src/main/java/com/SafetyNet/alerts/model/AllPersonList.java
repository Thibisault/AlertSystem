package com.SafetyNet.alerts.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AllPersonList {

    private String lastName;
    private String address;
    private String age;
    private String email;
    private String[] medications;
    private String[] allergies;

    public AllPersonList() {
        super();
    }
}
