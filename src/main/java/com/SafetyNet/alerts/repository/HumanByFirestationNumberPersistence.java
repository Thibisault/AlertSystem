package com.SafetyNet.alerts.repository;

import com.SafetyNet.alerts.model.HumanByFirestationNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Component
public class HumanByFirestationNumberPersistence {

    private List<HumanByFirestationNumber> humanByFirestationNumberPersistences = new ArrayList<>();

    public HumanByFirestationNumberPersistence() {
        super();
    }
}
