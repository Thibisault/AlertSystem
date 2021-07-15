package com.SafetyNet.alerts.repository;

import com.SafetyNet.alerts.model.PersonByFirestationNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
@AllArgsConstructor
public class PersonByFirestaionNumberPersistance {

    private List<PersonByFirestationNumber> personByFirestationNumbers = new ArrayList<>();
    private int adultNumber = 0;
    private int childNumber = 0;

    public PersonByFirestaionNumberPersistance() {
        super();
    }
}
