package com.SafetyNet.alerts.repository;

import com.SafetyNet.alerts.model.FamillyAtAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Component
public class FamilyMemberPersistance {

    private List<FamillyAtAddress> minorList = new ArrayList<>();
    private List<FamillyAtAddress> majorList = new ArrayList<>();

    public FamilyMemberPersistance() {
        super();
    }
}
