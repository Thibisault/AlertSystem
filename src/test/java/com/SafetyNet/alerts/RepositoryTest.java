package com.SafetyNet.alerts;

import com.SafetyNet.alerts.model.*;
import com.SafetyNet.alerts.repository.*;
import com.SafetyNet.alerts.service.AlertService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@ComponentScan({"com.SafetyNet.alerts"})
@EntityScan("com.SafetyNet.alerts")
@EnableJpaRepositories("com.SafetyNet.alerts")
public class RepositoryTest {

    private AlertPersistance alertPersistance = new AlertPersistance();
    private FamilyMemberPersistance familyMemberPersistance = new FamilyMemberPersistance();
    private FloodStationPersonPersistance floodStationPersonPersistance = new FloodStationPersonPersistance();
    private HumanByFirestationNumberPersistence humanByFirestationNumberPersistence = new HumanByFirestationNumberPersistence();
    private PersonByFirestaionNumberPersistance personByFirestaionNumberPersistance = new PersonByFirestaionNumberPersistance();

    AlertService alertServiceTest;

    @Autowired
    ObtainRandomPersistance obtainRandomPersistance;

    int objectNumberGeneration = 10;

    @BeforeEach
    public void setup() {
        alertServiceTest = new AlertService();
        alertServiceTest.setAlertPersistance(obtainRandomPersistance.obtainRandomAlertPersistance(objectNumberGeneration));
    }

    @AfterEach
    public void afterSetup() {
        alertServiceTest.getAlertPersistance().getPersons().clear();
        alertServiceTest.getAlertPersistance().getFirestations().clear();
        alertServiceTest.getAlertPersistance().getMedicalrecords().clear();
    }

    @Test
    void contextLoads() {
    }

    @Test
    void AlertPersistance(){
        List<Person> personList = alertServiceTest.listPerson();
        List<Firestation> firestationList = alertServiceTest.listFirestation();
        List<Medicalrecord> medicalrecordList = alertServiceTest.listMedicalrecord();
        int countPersonNumber = 0;
        int countFirestationNumber = 0;
        int countMedicalRecordNumber = 0;

        for (int i = 0; i < personList.size(); i++) {
            for (Person person : personList) {
                if (person.getFirstName() == alertServiceTest.getAlertPersistance().getPersons().get(i).getFirstName() &&
                person.getLastName() == alertServiceTest.getAlertPersistance().getPersons().get(i).getLastName()){
                    countPersonNumber = countPersonNumber +1;
                }
            }
        }

        for (int i = 0; i < firestationList.size(); i++){
            for (Firestation firestation : firestationList){
                if (firestation.getAddress() == alertServiceTest.getAlertPersistance().getFirestations().get(i).getAddress() &&
                        firestation.getStation() == alertServiceTest.getAlertPersistance().getFirestations().get(i).getStation()){
                    countFirestationNumber = countFirestationNumber +1;
                }
            }
        }

        for (int i = 0; i< medicalrecordList.size(); i++){
            for (Medicalrecord medicalrecord : medicalrecordList){
                if (medicalrecord.getFirstName() == alertServiceTest.getAlertPersistance().getMedicalrecords().get(i).getFirstName() &&
                medicalrecord.getLastName() == alertServiceTest.getAlertPersistance().getMedicalrecords().get(i).getLastName()){
                    countMedicalRecordNumber = countMedicalRecordNumber +1;
                }
            }
        }

        assertTrue(countPersonNumber == personList.size() && countFirestationNumber == firestationList.size() &&
                countMedicalRecordNumber == medicalrecordList.size());
    }

    @Test
    void FamilyMemberPersistance(){
        List<FamillyAtAddress> famillyAtAddressListMinor = new ArrayList<>();
        List<FamillyAtAddress> famillyAtAddressListMajor = new ArrayList<>();
        FamillyAtAddress famillyAtAddressMinor = new FamillyAtAddress();
        FamillyAtAddress famillyAtAddressMajor = new FamillyAtAddress();

        famillyAtAddressMinor.setFirstName("Patient enfant");
        famillyAtAddressMinor.setLastName("Un");
        famillyAtAddressMinor.setAge("15 ans");
        famillyAtAddressListMinor.add(famillyAtAddressMinor);
        familyMemberPersistance.setMinorList(famillyAtAddressListMinor);

        famillyAtAddressMajor.setFirstName("Patient adulte");
        famillyAtAddressMajor.setLastName("Deux");
        famillyAtAddressMajor.setAge("49 ans");
        famillyAtAddressListMajor.add(famillyAtAddressMajor);
        familyMemberPersistance.setMajorList(famillyAtAddressListMajor);


        assertTrue(familyMemberPersistance.getMinorList().get(0).getFirstName().equalsIgnoreCase("Patient enfant") &&
                familyMemberPersistance.getMajorList().get(0).getFirstName().equalsIgnoreCase("Patient adulte") &&
                familyMemberPersistance.getMinorList().get(0).getLastName().equalsIgnoreCase("un") &&
                familyMemberPersistance.getMajorList().get(0).getLastName().equalsIgnoreCase("Deux") &&
                familyMemberPersistance.getMinorList().get(0).getAge().equalsIgnoreCase("15 ans") &&
                familyMemberPersistance.getMajorList().get(0).getAge().equalsIgnoreCase("49 ans") &&
                familyMemberPersistance.getMajorList().size() == 1 && familyMemberPersistance.getMinorList().size() == 1);
    }

    @Test
    void FloodStationPersonPersistance(){
        List<FloodStationPerson> floodStationPersonList = new ArrayList<>();
        FloodStationPerson floodStationPerson = new FloodStationPerson();
        Map<String, List<FloodStationPerson>> floodMap = new HashMap<>();
        String address = "Paris";
        Object obj = new Object();

        floodStationPerson.setAge("20 ans");
        floodStationPerson.setLastName("Trombine");
        floodStationPerson.setAllergies(new String[]{"Tout"});
        floodStationPerson.setMedications(new String[]{"Des choses et d'autres"});
        floodStationPerson.setPhone("08-05-04-00-00-23");
        floodStationPersonList.add(floodStationPerson);
        floodMap.put(address, floodStationPersonList);
        floodStationPersonPersistance.setPersonByAddress(floodMap);

        for (Map.Entry mapEntry : floodMap.entrySet()){
            if (mapEntry.getKey() == address){
                obj = mapEntry.getValue();
            }
        }

        String test = obj.toString();

        assertTrue(test.contains("20 ans") &&
                test.contains("Trombine") &&
                test.contains("Tout") &&
                test.contains("Des choses et d'autres") &&
                test.contains("08-05-04-00-00-23"));
    }

    @Test
    void HumanByFirestationNumberPersistence(){
        List<HumanByFirestationNumber> humanByFirestationNumberList = new ArrayList<>();
        HumanByFirestationNumber humanByFirestationNumber = new HumanByFirestationNumber();
        humanByFirestationNumber.setFirestationNumber("1");
        humanByFirestationNumber.setAllergies(new String[]{"Porc"});
        humanByFirestationNumber.setPhone("01-010-01-01-32");
        humanByFirestationNumber.setAge("151");
        humanByFirestationNumber.setMedications(new String[]{"Proche"});
        humanByFirestationNumber.setLastName("Brader");
        String str = humanByFirestationNumber.toString();
        humanByFirestationNumberList.add(humanByFirestationNumber);
        humanByFirestationNumberPersistence.setHumanByFirestationNumberPersistences(humanByFirestationNumberList);
        String maxLenght = "HumanByFirestationNumber(firestationNumber=1, lastName=Brader, phone=01-010-01-01-32, age=151, medications=[Proche], allergies=[Porc])";
        String medicamentation = "";
        String allergies = "";

        for (String str1 : humanByFirestationNumberPersistence.getHumanByFirestationNumberPersistences().get(0).getMedications()){
            medicamentation = medicamentation + str1;
            }
        for (String str2 : humanByFirestationNumberPersistence.getHumanByFirestationNumberPersistences().get(0).getAllergies()){
            allergies = allergies + str2;
        }

        assertTrue(str.contains(humanByFirestationNumberPersistence.getHumanByFirestationNumberPersistences().get(0).getLastName()) &&
                        str.contains(humanByFirestationNumberPersistence.getHumanByFirestationNumberPersistences().get(0).getFirestationNumber()) &&
                        str.contains(humanByFirestationNumberPersistence.getHumanByFirestationNumberPersistences().get(0).getPhone()) &&
                        str.contains(humanByFirestationNumberPersistence.getHumanByFirestationNumberPersistences().get(0).getAge()) &&
                        str.contains(medicamentation) &&
                        str.contains(allergies) &&
                        humanByFirestationNumberPersistence.getHumanByFirestationNumberPersistences().size() > 0 &&
                        humanByFirestationNumberPersistence.getHumanByFirestationNumberPersistences().size() ==humanByFirestationNumberList.size());
    }

    @Test
    void PersonByFirestaionNumberPersistance(){
        List<Person> personList = alertServiceTest.listPerson();
        List<PersonByFirestationNumber> personByFirestationNumberList = new ArrayList<>();

        PersonByFirestationNumber personByFirestationNumber = new PersonByFirestationNumber();
        int countPersonInFirestationZone = 0;
        int childNumber = 0;
        int adultNumber = 0;

        for (Person person : personList){
            personByFirestationNumber.setLastName(person.getLastName());
            personByFirestationNumber.setFirstName(person.getFirstName());
            personByFirestationNumber.setAddress(person.getAddress());
            personByFirestationNumber.setPhone(person.getPhone());
            personByFirestationNumberList.add(personByFirestationNumber);
        }
        personByFirestaionNumberPersistance.setPersonByFirestationNumbers(personByFirestationNumberList);

        for (int i = 0; i < personByFirestationNumberList.size(); i ++) {
            for (PersonByFirestationNumber personByFirestationNumber1 : personByFirestationNumberList) {
                if (personByFirestationNumber1.getFirstName().equalsIgnoreCase(personByFirestaionNumberPersistance.getPersonByFirestationNumbers().get(i).getFirstName()) &&
                        personByFirestationNumber1.getLastName().equalsIgnoreCase(personByFirestaionNumberPersistance.getPersonByFirestationNumbers().get(i).getLastName())){
                    countPersonInFirestationZone = countPersonInFirestationZone +1;
                    break;
                }
            }
        }

        for (int i = 0; i < personByFirestationNumberList.size(); i++){
            if (childNumber == adultNumber -1){
                childNumber = childNumber +1;
            } else {
                adultNumber = adultNumber +1;
            }
        }
        personByFirestaionNumberPersistance.setChildNumber(childNumber);
        personByFirestaionNumberPersistance.setAdultNumber(adultNumber);

        assertTrue(countPersonInFirestationZone == personByFirestaionNumberPersistance.getPersonByFirestationNumbers().size() &&
                personByFirestaionNumberPersistance.getAdultNumber() == adultNumber &&
                personByFirestaionNumberPersistance.getChildNumber() == childNumber);
    }
}
