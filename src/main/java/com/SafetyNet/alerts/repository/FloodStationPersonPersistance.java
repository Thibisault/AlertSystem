package com.SafetyNet.alerts.repository;

import com.SafetyNet.alerts.model.FloodStationPerson;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Component
public class FloodStationPersonPersistance {

    private Map<String, List<FloodStationPerson>> personByAddress = new HashMap<>();

    public FloodStationPersonPersistance() {
        super();
    }
}
