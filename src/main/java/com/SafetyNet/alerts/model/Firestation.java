package com.SafetyNet.alerts.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Firestation {

    private String address;
    private String station;

    public Firestation(){
        super();
    }

}
