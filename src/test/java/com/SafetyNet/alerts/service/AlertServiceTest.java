package com.SafetyNet.alerts.service;

import com.SafetyNet.alerts.ObtainRandomPersistance;
import com.SafetyNet.alerts.model.*;
import com.SafetyNet.alerts.repository.FamilyMemberPersistance;
import com.SafetyNet.alerts.repository.FloodStationPersonPersistance;
import com.SafetyNet.alerts.repository.HumanByFirestationNumberPersistence;
import com.SafetyNet.alerts.repository.PersonByFirestaionNumberPersistance;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = {AlertService.class})
@ComponentScan({"com.SafetyNet.alerts"})
@EntityScan("com.SafetyNet.alerts")
@EnableJpaRepositories("com.SafetyNet.alerts")
class AlertServiceTest {

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
    void readJsonFile() {
    }

    @Test
    void writeJsonFile() {
    }

    @Test
    void listPerson() {
        List<Person> personList = alertServiceTest.listPerson();
        System.out.println(personList);
        assertTrue(personList != null && personList.size() == objectNumberGeneration);
    }

    @Test
    void listFirestation() {
        List<Firestation> firestationList = alertServiceTest.listFirestation();
        System.out.println(firestationList);
        assertTrue(firestationList != null && firestationList.size() == objectNumberGeneration / 2);
    }

    @Test
    void listMedicalrecord() {
        List<Medicalrecord> medicalrecordList = alertServiceTest.listMedicalrecord();
        System.out.println(medicalrecordList);
        assertTrue(medicalrecordList != null && medicalrecordList.size() == objectNumberGeneration);
    }

    @Test
    void saveAlertPersistance() {
        alertServiceTest.writeJsonFile("C:\\Users\\Thibault\\TestParkings\\alerts\\src\\main\\resources/fichierTest.json", alertServiceTest.listPerson());
        assertTrue(alertServiceTest.listPerson() != null && alertServiceTest.listPerson().size() == objectNumberGeneration);

    }

    //TODO : Endpoints

    @Test
    void addPerson() throws IOException {
        List<Person> personList = alertServiceTest.listPerson();
        int nombrePersonsAvant = alertServiceTest.listPerson().size();
        alertServiceTest.addPerson(personList.get(0));
        int nombrePersonsApres = alertServiceTest.listPerson().size();
        assertTrue(nombrePersonsAvant + 1 == nombrePersonsApres);
    }

    @Test
    void deletePerson() {
        List<Person> personList = alertServiceTest.listPerson();
        int nombrePersonsAvant = alertServiceTest.listPerson().size();
        alertServiceTest.deletePerson(personList.get(0).getFirstName(), personList.get(0).getLastName());
        int nombrePersonsApres = alertServiceTest.listPerson().size();
        assertTrue(nombrePersonsAvant == nombrePersonsApres + 1);
    }

    @Test
    void changePerson() {
        List<Person> personList = alertServiceTest.listPerson();
        Person newPersonTest = new Person();
        newPersonTest.setFirstName(personList.get(0).getFirstName());
        newPersonTest.setLastName(personList.get(0).getLastName());
        newPersonTest.setAddress("L'adresse qui a changé pour le test est celle ci !");
        String addressPersonBeforeChange = personList.get(0).getAddress();
        alertServiceTest.changePerson(newPersonTest);
        String addressPersonAfterChange = personList.get(0).getAddress();
        assertTrue(addressPersonBeforeChange != addressPersonAfterChange
                && addressPersonAfterChange.equalsIgnoreCase("L'adresse qui a changé pour le test est celle ci !"));
    }

    @Test
    void addFirestation() {
        List<Firestation> firestationList = alertServiceTest.listFirestation();
        int nombreFirestationAvant = alertServiceTest.listFirestation().size();
        alertServiceTest.addFirestation(firestationList.get(0));
        int nombreFirestaionApres = alertServiceTest.listFirestation().size();
        assertTrue(nombreFirestationAvant + 1 == nombreFirestaionApres);
    }

    @Test
    void changeFirestation() {
        List<Firestation> firestationList = alertServiceTest.listFirestation();
        Firestation newFirestationTest = new Firestation();
        String stationNewChange = String.valueOf(Integer.valueOf(firestationList.get(0).getStation())+1);
        newFirestationTest.setAddress(firestationList.get(0).getAddress());
        newFirestationTest.setStation(stationNewChange);
        String stationFirestationBeforeChange = firestationList.get(0).getStation();
        alertServiceTest.changeFirestation(newFirestationTest);
        String stationFirestationAfterChange = firestationList.get(0).getStation();
        assertTrue(stationFirestationBeforeChange != stationFirestationAfterChange
                 && stationFirestationAfterChange.equalsIgnoreCase(stationNewChange));
}

    @Test
    void deleteFirestation() {
        List<Firestation> firestationList = alertServiceTest.listFirestation();
        int nombreFirestationBefore = alertServiceTest.listFirestation().size();
        alertServiceTest.deleteFirestation(firestationList.get(0).getAddress());
        int nombreFirestationAfter = alertServiceTest.listFirestation().size();
        assertTrue(nombreFirestationBefore == nombreFirestationAfter +1);
    }

    @Test
    void addMedicalRecord() {
        List<Medicalrecord> medicalrecordList = alertServiceTest.listMedicalrecord();
        int nombreMedicalRecordBefore = alertServiceTest.listMedicalrecord().size();
        alertServiceTest.addMedicalRecord(medicalrecordList.get(0));
        int nombreMedicalRecordAfter = alertServiceTest.listMedicalrecord().size();
        assertTrue(nombreMedicalRecordBefore + 1 == nombreMedicalRecordAfter);
    }

    @Test
    void changeMedicalRecord() {
        List<Medicalrecord> medicalrecordList = alertServiceTest.listMedicalrecord();
        Medicalrecord newMedicalRecordTest = new Medicalrecord();
        newMedicalRecordTest.setFirstName(medicalrecordList.get(0).getFirstName());
        newMedicalRecordTest.setLastName(medicalrecordList.get(0).getLastName());
        newMedicalRecordTest.setBirthdate("01/01/0001");
        String birthdayBeforeChange = medicalrecordList.get(0).getBirthdate();
        alertServiceTest.changeMedicalRecord(newMedicalRecordTest);
        String birthdayAfterChange = medicalrecordList.get(0).getBirthdate();
        assertTrue(birthdayBeforeChange != birthdayAfterChange
                && birthdayAfterChange.equals("01/01/0001"));
    }

    @Test
    void deleteMedicalRecord() {
        List<Medicalrecord> medicalrecordList = alertServiceTest.listMedicalrecord();

        int nombreMedicalRecordBefore = medicalrecordList.size();
        alertServiceTest.deleteMedicalRecord(medicalrecordList.get(1).getFirstName(), medicalrecordList.get(1).getLastName());
        int nombreMedicalRecordAfter = medicalrecordList.size();
        assertTrue(nombreMedicalRecordBefore == nombreMedicalRecordAfter +1);
    }

    //TOdo : Url

    @Test
    void countChildByAddress() throws ParseException {
        List<Person> personList = alertServiceTest.listPerson();
        FamilyMemberPersistance familyMemberPersistance =
                alertServiceTest.countChildByAddress(personList.get(1).getAddress());
        assertTrue(familyMemberPersistance.getMinorList().size() == 1);
    }

    @Test
    void getPersonInFirestationZone() {
        List<Person> personList = alertServiceTest.listPerson();
        List<Firestation> firestationList = alertServiceTest.listFirestation();
        List <String> phoneNumber = alertServiceTest.getPersonInFirestationZone(firestationList.get(1).getStation());
        int numberAddressForStation = 0;
            for (Firestation station : firestationList){
                if (station.getStation() == firestationList.get(1).getStation()) {
                    for (Person person : personList) {
                        if (person.getAddress() == station.getAddress()) {
                            numberAddressForStation = numberAddressForStation + 1;
                        }
                    }
                }
            }
        assertTrue(phoneNumber.size() == numberAddressForStation);
    }

    @Test
    void getFirestationForAdrdress() throws ParseException {
        List<Person> personList = alertServiceTest.listPerson();
        HumanByFirestationNumberPersistence humanByFirestationNumberPersistence = alertServiceTest.getFirestationForAdrdress(personList.get(1).getAddress());
        int personNumberAtAddress = 0;
        for (Person person : personList){
            if (person.getAddress().equalsIgnoreCase(personList.get(1).getAddress())){
                personNumberAtAddress = personNumberAtAddress +1;
            }
        }
        assertTrue(humanByFirestationNumberPersistence.getHumanByFirestationNumberPersistences().size() == personNumberAtAddress);
    }

    @Test
    void obtainPersonByAddress() throws ParseException {
        List<Firestation> firestationList = alertServiceTest.listFirestation();
        List<Person> personList = alertServiceTest.listPerson();
        FloodStationPersonPersistance floodStationPersonPersistance = alertServiceTest.obtainPersonByAddress(firestationList.get(1).getStation());
        int personNumberLivingThisAddress = 0;
        for (Person person : personList){
            if (person.getAddress() == firestationList.get(1).getAddress()){
                personNumberLivingThisAddress = personNumberLivingThisAddress +1;
            }
        }
        assertTrue(personNumberLivingThisAddress == floodStationPersonPersistance.getPersonByAddress().size());
    }

    @Test
    void obtainAllPersonList() throws ParseException {
        List<AllPersonList> allPersonLists = alertServiceTest.obtainAllPersonList();
        assertTrue(allPersonLists.size() == objectNumberGeneration);
    }

    @Test
    void haveEmailsFromAllPersons() {
        List<Person> personList = alertServiceTest.listPerson();
        List<String> emailList = alertServiceTest.haveEmailsFromAllPersons(personList.get(1).getCity());
        int personNumberInThisCity = 0;
        for (Person person : personList){
            if (person.getCity() == personList.get(1).getCity()){
                personNumberInThisCity = personNumberInThisCity +1;
            }
        }
        assertTrue(personNumberInThisCity == emailList.size());
    }

    @Test
    void obtaintListPersonByFireStation() throws ParseException {
        List<Person> personList = alertServiceTest.listPerson();
        List<Firestation> firestationList = alertServiceTest.listFirestation();
        int personNumberCoverByThisStation = 0;
        PersonByFirestaionNumberPersistance personByFirestaionNumberPersistance
                = alertServiceTest.obtaintListPersonByFireStation(firestationList.get(1).getStation());
            for (Firestation firestation : firestationList){
                for (Person person : personList){
                    if (firestation.getStation() == firestationList.get(1).getStation()){
                        if (firestation.getAddress() == person.getAddress()){
                        personNumberCoverByThisStation = personNumberCoverByThisStation +1;
                    }
                }
            }
        }
        assertTrue(personByFirestaionNumberPersistance.getPersonByFirestationNumbers().size() == personNumberCoverByThisStation);
                }
}