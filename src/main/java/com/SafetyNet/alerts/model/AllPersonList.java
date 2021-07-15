package com.SafetyNet.alerts.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
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
