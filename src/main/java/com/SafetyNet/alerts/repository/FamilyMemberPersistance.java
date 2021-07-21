package com.SafetyNet.alerts.repository;

import com.SafetyNet.alerts.model.FamillyAtAddress;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
public class FamilyMemberPersistance {

    private List<FamillyAtAddress> minorList = new ArrayList<>();
    private List<FamillyAtAddress> majorList = new ArrayList<>();

    public FamilyMemberPersistance() {
        super();
    }
}
