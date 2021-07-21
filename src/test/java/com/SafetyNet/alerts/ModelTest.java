package com.SafetyNet.alerts;

import com.SafetyNet.alerts.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import static org.junit.Assert.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@ComponentScan({"com.SafetyNet.alerts"})
@EntityScan("com.SafetyNet.alerts")
@EnableJpaRepositories("com.SafetyNet.alerts")
public class ModelTest {

    private AllPersonList allPersonList = new AllPersonList();
    private FamillyAtAddress famillyAtAddress = new FamillyAtAddress();
    private Firestation firestation = new Firestation();
    private FloodStationPerson floodStationPerson = new FloodStationPerson();
    private HumanByFirestationNumber humanByFirestationNumber = new HumanByFirestationNumber();
    private Medicalrecord medicalrecord = new Medicalrecord();
    private Person person = new Person();
    private PersonByFirestationNumber personByFirestationNumber = new PersonByFirestationNumber();

    @Test
    void contextLoads() {
    }

    @Test
    void allPersonList(){
        String str = allPersonList.toString();

        allPersonList.setLastName("Thibault");
        allPersonList.setAddress("Chez Thibault");
        allPersonList.setAge("L'âge de Thibault");
        allPersonList.setEmail("Thibault.mail");
        allPersonList.setMedications(new String[]{"Eau de thibault 5000mg"});
        allPersonList.setAllergies(new String[]{"Tout sauf Thibault"});

        String maxLenght = "AllPersonList(lastName=null, address=null, age=null, email=null, medications=null, allergies=null)";
        assertTrue(str.contains("lastName") &&
                str.contains("address") &&
                str.contains("age") &&
                str.contains("email") &&
                str.contains("medications") &&
                str.contains("allergies") &&
                str.length() == maxLenght.length() &&
                allPersonList.getLastName() != null &&
                allPersonList.getAddress() != null &&
                allPersonList.getAge() != null &&
                allPersonList.getEmail() != null &&
                allPersonList.getMedications() != null &&
                allPersonList.getAllergies() != null );
    }

    @Test
    void famillyAtAddress(){
        String str = famillyAtAddress.toString();
        String maxLenght = "FamillyAtAddress(firstName=null, lastName=null, age=null)";
        assertTrue(str.contains("firstName") &&
                str.contains("lastName") &&
                str.contains("age") &&
                str.length() == maxLenght.length());
    }

    @Test
    void firestastion(){
        String str = firestation.toString();
        String maxLenght = "Firestation(address=null, station=null)";
        assertTrue(str.contains("address") &&
                str.contains("station") &&
                str.length() == maxLenght.length());
    }

    @Test
    void floodStationPerson(){
        String str = floodStationPerson.toString();
        String maxLenght = "FloodStationPerson(lastName=null, phone=null, age=null, medications=null, allergies=null)";
        assertTrue(str.contains("FloodStationPerson") &&
                str.contains("phone") &&
                str.contains("age") &&
                str.contains("medications") &&
                str.contains("allergies") &&
                str.length() == maxLenght.length());

    }

    @Test
    void humanByFirestationNumber(){
        String str = humanByFirestationNumber.toString();
        String maxLenght = "HumanByFirestationNumber(firestationNumber=null, lastName=null, phone=null, age=null, medications=null, allergies=null)";
        assertTrue(str.contains("firestationNumber") &&
                str.contains("lastName") &&
                str.contains("phone") &&
                str.contains("age") &&
                str.contains("medications") &&
                str.contains("allergies") &&
                str.length() == maxLenght.length());
    }

    @Test
    void medicalRecord(){
        String str = medicalrecord.toString();
        String maxLenght = "Medicalrecord(firstName=null, lastName=null, birthdate=null, medications=null, allergies=null)";
        assertTrue(str.contains("firstName") &&
                str.contains("lastName") &&
                str.contains("birthdate") &&
                str.contains("medications") &&
                str.contains("allergies") &&
                str.length() == maxLenght.length());

    }

    @Test
    void person(){
        String str = person.toString();
        String maxLenght = "Person(firstName=null, lastName=null, address=null, city=null, zip=null, phone=null, email=null)";
        assertTrue(str.contains("firstName") &&
                str.contains("lastName") &&
                str.contains("address") &&
                str.contains("city") &&
                str.contains("zip") &&
                str.contains("phone") &&
                str.contains("email") &&
                str.length() == maxLenght.length());
    }

    @Test
    void personByFirestationNumber(){
        String str = personByFirestationNumber.toString();
        String maxLenght = "PersonByFirestationNumber(firstName=null, lastName=null, address=null, phone=null)";
        assertTrue(str.contains("firstName") &&
                str.contains("lastName") &&
                str.contains("address") &&
                str.contains("phone") &&
                str.length() == maxLenght.length());
    }
}
