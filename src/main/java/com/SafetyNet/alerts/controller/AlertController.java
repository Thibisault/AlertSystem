package com.SafetyNet.alerts.controller;

import com.SafetyNet.alerts.model.*;
import com.SafetyNet.alerts.repository.FamilyMemberPersistance;
import com.SafetyNet.alerts.repository.FloodStationPersonPersistance;
import com.SafetyNet.alerts.repository.HumanByFirestationNumberPersistence;
import com.SafetyNet.alerts.repository.PersonByFirestaionNumberPersistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import com.SafetyNet.alerts.service.AlertService;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RestController
@RequestMapping("/*")
public class AlertController {

    @Autowired
    private AlertService alertService;

    public AlertController(AlertService alertService){
        this.alertService = alertService;
    }

    @GetMapping("/persons/list")
    public List<Person> listPerson(){
        return alertService.listPerson();
    }

    @GetMapping("/firestations/list")
    public List<Firestation> listFirestation(){
        return alertService.listFirestation();
    }

    @GetMapping("/medicalrecords/list")
    public List<Medicalrecord> listMedicalRecords(){
        return alertService.listMedicalrecord();
    }

    @GetMapping("/tout/list")
    public List listTout(){
        ArrayList list = new ArrayList<>();
        list.addAll(Arrays.asList(alertService.listPerson()));
        list.addAll(Arrays.asList(alertService.listFirestation()));
        list.addAll(Arrays.asList(alertService.listMedicalrecord()));
        System.out.println(list);
        return list;
    }

    @PostMapping("/person")
    public Person addPersonToList(@RequestBody Person person) throws IOException {
        alertService.addPerson(person);
        return person;
    }

    @PutMapping("/person")
    public Person changePersonInList(@RequestBody Person person){
        return alertService.changePerson(person);
    }

    @DeleteMapping("/person/{firstName}/{lastName}")
    public Person deletePersonFromList(@PathVariable String firstName, @PathVariable String lastName){
        return alertService.deletePerson(firstName, lastName);
    }


    @PostMapping("/firestation")
    public Firestation addFirestationToList(@RequestBody Firestation firestation){
        alertService.addFirestation(firestation);
        return firestation;
    }

    @PutMapping("/firestation")
    public Firestation changerFirestationInList(@RequestBody Firestation firestation){
        return alertService.changeFirestation(firestation);
    }

    @DeleteMapping("/firestation/{address}")
    public Firestation deleteFirestationFromList(@PathVariable String address){
        return alertService.deleteFirestation(address);
    }


    @PostMapping("/medicalRecord")
    public Medicalrecord addMedicalRecordToList(@RequestBody Medicalrecord medicalRecord){
        alertService.addMedicalRecord(medicalRecord);
        return medicalRecord;
    }

    @PutMapping("/medicalRecord")
    public Medicalrecord changerMedicalRecordInList(@RequestBody Medicalrecord medicalRecord){
        return alertService.changeMedicalRecord(medicalRecord);
    }

    @DeleteMapping("/medicalRecord/{firstName}/{lastName}")
    public Medicalrecord deleteMedicalRecordFromList(@PathVariable String firstName, @PathVariable String lastName){
        return alertService.deleteMedicalRecord(firstName, lastName);
    }


    //TODO : URL

    @PostMapping("/firestation/{stationNumber}")
    public PersonByFirestaionNumberPersistance avoirListeDePersonParFirestation(@PathVariable String stationNumber) throws ParseException {
        return alertService.obtaintListPersonByFireStation(stationNumber);
    }

    @PostMapping("/childAlert/{address}")
    public FamilyMemberPersistance countChildren(@PathVariable String address) throws ParseException {
        return alertService.countChildByAddress(address);
    }

    @PostMapping("/phoneAlert/{firestationNumber}")
    public List<String> sendUrgencySms(@PathVariable String firestationNumber){
        return alertService.getPersonInFirestationZone(firestationNumber);
    }

    @PostMapping("/fire/{address}")
    public HumanByFirestationNumberPersistence firesationDeserveThatAddress(@PathVariable String address) throws ParseException {
        return alertService.getFirestationForAdrdress(address);
    }

    @PostMapping("/flood/stations/{firestationNumber}")
    public FloodStationPersonPersistance personsByAddress(@PathVariable String firestationNumber) throws ParseException {
        return alertService.obtainPersonByAddress(firestationNumber);
    }

    @PostMapping("/personInfo")
    public List<AllPersonList> allPersonList() throws ParseException {
        return alertService.obtainAllPersonList();
    }

    @PostMapping("/communityEmail?city=<city>")
    public List<String> haveEmailFromAllPerson(@PathVariable String city){

        return alertService.haveEmailsFromAllPersons(city);
    }
}

