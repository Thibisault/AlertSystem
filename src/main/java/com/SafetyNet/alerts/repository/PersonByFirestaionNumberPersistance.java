package com.SafetyNet.alerts.repository;

import com.SafetyNet.alerts.model.PersonByFirestationNumber;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
public class PersonByFirestaionNumberPersistance {

    private List<PersonByFirestationNumber> personByFirestationNumbers = new ArrayList<>();
    private int adultNumber = 0;
    private int childNumber = 0;

    public PersonByFirestaionNumberPersistance() {
        super();
    }
}
