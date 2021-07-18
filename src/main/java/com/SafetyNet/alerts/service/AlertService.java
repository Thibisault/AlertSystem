package com.SafetyNet.alerts.service;

import com.SafetyNet.alerts.model.*;
import com.SafetyNet.alerts.repository.*;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.List;


@Service
@Data
public class AlertService {

    AlertPersistance alertPersistance = new AlertPersistance();

    FamilyMemberPersistance familyMemberPersistance = new FamilyMemberPersistance();

    HumanByFirestationNumberPersistence humanByFirestationNumberPersistence = new HumanByFirestationNumberPersistence();

    PersonByFirestaionNumberPersistance personByFirestaionNumberPersistance = new PersonByFirestaionNumberPersistance();

    FloodStationPersonPersistance floodStationPersistance = new FloodStationPersonPersistance();


    public AlertService() {
        this.alertPersistance = JsonFile.readJsonFile("fichierInitial.json");
    }

    /**
     * Permet de convertir la date pour determiner l'âge des habitants.
     * @param dateToConvert
     * @return
     */
    private LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Permet d'obtenir toutes les informations du ficher Json de la table Person.
     * @return La liste des personnes.
     */
    public List<Person> listPerson() {
        return alertPersistance.getPersons();
    }
    /**
     * Permet d'obtenir toutes les informations du ficher Json de la table Firestation.
     * @return La liste des casernes de pompiers.
     */
    public List<Firestation> listFirestation() {
        return alertPersistance.getFirestations();
    }
    /**
     * Permet d'obtenir toutes les informations du fichier Json de la table Medicalrecord.
     * @return La liste de tout les dossiers médicaux.
     */
    public List<Medicalrecord> listMedicalrecord() {
        return alertPersistance.getMedicalrecords();
    }
    /**
     * Permet d'enregistrer en mémoire toutes les informations du fichier Json en entrée ("fichierInitial")
     * @param alertPersistance
     * @return La liste de toutes les personnes, la liste de toutes les casernes de pompiers et la liste de tout les dossiers médicaux.
     */
    public  AlertPersistance saveAlertPersistance(AlertPersistance alertPersistance) {
        this.alertPersistance = alertPersistance;
        return alertPersistance;
    }

    //Todo : Endpoints Person

    /**
     * Ajouter une nouvelle personne à la liste.
     * @param person L'identifiant de la personne que l'on souhaite ajouter.
     * @return La nouvelle personne ajouté.
     * @throws IOException
     */
    public Person addPerson(Person person) throws IOException {
        alertPersistance.getPersons().add(person);
        JsonFile.writeJsonFile("fichierTest.json", alertPersistance);
        return person;
    }
    /**
     * Met à jour une personne existante.
     * @param newPerson La personne avec les modifications qui va remplacer l'ancienne personne.
     * @return La nouvelle personne avec les modifications.
     */
    public Person changePerson(Person newPerson) {
        List<Person> personList = alertPersistance.getPersons();
        for (Person oldPerson : personList) {
            if (oldPerson.getFirstName().equalsIgnoreCase(newPerson.getFirstName()) && oldPerson.getLastName().equalsIgnoreCase(newPerson.getLastName())) {
                oldPerson.setAddress(newPerson.getAddress());
                oldPerson.setCity(newPerson.getCity());
                oldPerson.setZip(newPerson.getZip());
                oldPerson.setPhone(newPerson.getPhone());
                oldPerson.setEmail(newPerson.getEmail());
                JsonFile.writeJsonFile("fichierTest.json", alertPersistance);
                return oldPerson;
            }
        }
        return null;
    }
    /**
     * Supprime une personne.
     * @param firstName Le prénom de la personne à supprimer.
     * @param lastName Le nom de la personne à supprimer.
     * @return Un objet person json null.
     */
    public Person deletePerson(String firstName, String lastName) {
        List<Person> personList = alertPersistance.getPersons();
        for (Person person : personList) {
            if (person.getFirstName().equalsIgnoreCase(firstName) && person.getLastName().equalsIgnoreCase(lastName)) {
                personList.remove(person);
                JsonFile.writeJsonFile("fichierTest.json", alertPersistance);
                return person;
            }
        }
        return null;
    }

    //Todo : Endpoints Firestation

    /**
     * Ajouter une nouvelle caserne et adresse.
     * @param firestation La caserne à ajouter.
     * @return La nouvelle caserne qui vient d'être ajouté.
     */
    public Firestation addFirestation(Firestation firestation) {
        alertPersistance.getFirestations().add(firestation);
        JsonFile.writeJsonFile("fichierTest.json", alertPersistance);
        return firestation;
    }
    /**
     * Met à jour le numéro de la caserne de pompier d'une adresse.
     * @param newFirestation La nouvelle caserne de pompier avec les informations qui vont remplacer l'ancienne.
     * @return La nouvelle caserne de pompier avec ses nouvelles informations.
     */
    public Firestation changeFirestation(Firestation newFirestation) {
        List<Firestation> firestationList = alertPersistance.getFirestations();
        for (Firestation oldFirestation : firestationList) {
            if (oldFirestation.getAddress().equalsIgnoreCase(newFirestation.getAddress())) {
                oldFirestation.setStation(newFirestation.getStation());
                JsonFile.writeJsonFile("fichierTest.json", alertPersistance);
                return oldFirestation;
            }
        }
        return null;
    }
    /**
     * Supprimer l'identifiant de la caserne ou son adresse.
     * @param address L'adresse de la caserne concerné par la suppression d'une information.
     * @return Un objet firestation Json null ou avec une des deux informations supprimé.
     */
    public Firestation deleteFirestation(String address) {
        List<Firestation> firestationList = alertPersistance.getFirestations();
        for (Firestation firestation : firestationList) {
            if (firestation.getAddress().equalsIgnoreCase(address)) {
                firestationList.remove(firestation);
                JsonFile.writeJsonFile("fichierTest.json", alertPersistance);
                return firestation;
            }
        }
        return null;
    }

    //Todo : Endpoints MedicalRecord

    /**
     * Aujouter un dosser médical.
     * @param medicalRecord Le nouveau dossier médical à ajouter.
     * @return Le nouveau dossier médical ajouté.
     */
    public Medicalrecord addMedicalRecord(Medicalrecord medicalRecord) {
        alertPersistance.getMedicalrecords().add(medicalRecord);
        JsonFile.writeJsonFile("fichierTest.json", alertPersistance);
        return medicalRecord;
    }
    /**
     * Met à jour un dossier médical existant.
     * @param newMedicalRecord Le nouveau dossier médical d'une personne avec les modifications.
     * @return Le nouveaux dossier médical de la personne.
     */
    public Medicalrecord changeMedicalRecord(Medicalrecord newMedicalRecord) {
        List<Medicalrecord> medicalrecordList = alertPersistance.getMedicalrecords();
        for (Medicalrecord oldMedicalrecord : medicalrecordList) {
            if (oldMedicalrecord.getFirstName().equalsIgnoreCase(newMedicalRecord.getFirstName()) && oldMedicalrecord.getLastName().equalsIgnoreCase(newMedicalRecord.getLastName())) {
                oldMedicalrecord.setBirthdate(newMedicalRecord.getBirthdate());
                oldMedicalrecord.setMedications(newMedicalRecord.getMedications());
                oldMedicalrecord.setAllergies(newMedicalRecord.getAllergies());
                JsonFile.writeJsonFile("fichierTest.json", alertPersistance);
                return oldMedicalrecord;
            }
        }
        return null;
    }
    /**
     * Supprimer un dossier médical.
     * @param firstName Le prénom de la personne dont le dossier médical doit être supprimé.
     * @param lastName Le nom de la personne dont le dossier médical doit être supprimé.
     * @return Un objet json medicalrecord null.
     */
    public Medicalrecord deleteMedicalRecord(String firstName, String lastName) {
        List<Medicalrecord> medicalrecordList = alertPersistance.getMedicalrecords();
        for (Medicalrecord medicalrecord : medicalrecordList) {
            if (medicalrecord.getFirstName().equalsIgnoreCase(firstName) && medicalrecord.getLastName().equalsIgnoreCase(lastName)) {
                medicalrecordList.remove(medicalrecord);
                JsonFile.writeJsonFile("fichierTest.json", alertPersistance);
                return medicalrecord;
            }
        }
        return null;
    }


    //TODO : URL

    /**
     * Compte tout les enfants habitant à une adresse.
     * @param address L'identifiant de l'adresse.
     * @return La liste de tout les enfants qui vivent à une adresse.
     * @throws ParseException Dans le cas ou la date de naissance est au mauvais format.
     */
    public FamilyMemberPersistance countChildByAddress(String address) throws ParseException {
        List<Person> personList = alertPersistance.getPersons();
        List<Medicalrecord> medicalrecordList = alertPersistance.getMedicalrecords();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        GregorianCalendar calendar = new GregorianCalendar();
        FamilyMemberPersistance  familyMemberPersistance = new FamilyMemberPersistance();

        for (Person person : personList) {
            if (person.getAddress().equalsIgnoreCase(address)) {
                String firstName = person.getFirstName();
                String lastName = person.getLastName();
                for (Medicalrecord medicalrecord : medicalrecordList) {
                    if (medicalrecord.getFirstName().equalsIgnoreCase(firstName) && medicalrecord.getLastName().equalsIgnoreCase(lastName)) {
                        Date dateNaissance = format.parse(medicalrecord.getBirthdate());
                        Date dateJour = new Date(System.currentTimeMillis());
                        calendar.setTimeInMillis(dateNaissance.getTime());
                        calendar.add(Calendar.YEAR, 19);
                        LocalDate localeDateNaissance = convertToLocalDateViaInstant(dateNaissance);
                        LocalDate localeDateJour = convertToLocalDateViaInstant(dateJour);

                        int resultatAge = Period.between(localeDateNaissance, localeDateJour).getYears();

                        FamillyAtAddress famillyAtAddress = new FamillyAtAddress();
                        famillyAtAddress.setFirstName(firstName);
                        famillyAtAddress.setLastName(lastName);
                        famillyAtAddress.setAge(String.valueOf(resultatAge));

                        if (calendar.getTime().before(dateJour)) {
                            familyMemberPersistance.getMajorList().add(famillyAtAddress);
                        } else {
                            familyMemberPersistance.getMinorList().add(famillyAtAddress);
                        }
                    }
                }
            }
        }
        return familyMemberPersistance;
    }

    /**
     * retourne le numéro de téléphone de tout les habitants déservit par une caserne en particulier.
     * @param firestationNumber L'identifiant de la caserne.
     * @return La liste de tout les numéros de téléphone des habitants.
     */
    public List<String> getPersonInFirestationZone(String firestationNumber) {
        List<Person> personList = alertPersistance.getPersons();
        List<Firestation> firestationList = alertPersistance.getFirestations();
        String regroupFirestation;
        List<String> personDansZone = new ArrayList<>();

        for (Firestation firestation : firestationList) {
            if (firestation.getStation().equals(firestationNumber)) {
                regroupFirestation = firestation.getAddress();
                for (Person person : personList) {
                    if (person.getAddress().equalsIgnoreCase(regroupFirestation)) {
                        personDansZone.add(person.getPhone());
                    }
                }
            }
        }
        return personDansZone;
    }
    /**
     * Retourne en fonction d'une adresse donnée tout les habitants y vivant, et les casernes la deservant.
     * @param address L'identifiant de l'adresse.
     * @return La liste de tout les habitants vivant à cette adresse ainsi que le numéro de station de la caserne servant cette adress avec les inforamations : (numéro caserne, nom, téléphone, âge, médication, allérgies)
     * @throws ParseException Dans le cas ou la date de naissance est au mauvais format.
     */
    public HumanByFirestationNumberPersistence getFirestationForAdrdress(String address) throws ParseException {
        List<Person> personList = alertPersistance.getPersons();
        List<Firestation> firestationList = alertPersistance.getFirestations();
        List<Medicalrecord> medicalrecordList = alertPersistance.getMedicalrecords();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        GregorianCalendar calendar = new GregorianCalendar();
        humanByFirestationNumberPersistence.getHumanByFirestationNumberPersistences().clear();

        for (Person person : personList) {
            if (person.getAddress().equalsIgnoreCase(address)) {
                for (Firestation firestation : firestationList) {
                    if (person.getAddress().equals(firestation.getAddress())) {
                        for (Medicalrecord medicalrecord : medicalrecordList) {
                            if (medicalrecord.getFirstName().equalsIgnoreCase(person.getFirstName()) &&
                                    medicalrecord.getLastName().equalsIgnoreCase(person.getLastName())) {
                                Date dateNaissance = format.parse(medicalrecord.getBirthdate());
                                Date dateJour = new Date(System.currentTimeMillis());
                                calendar.setTimeInMillis(dateNaissance.getTime());
                                calendar.add(Calendar.YEAR, 19);
                                LocalDate localeDateNaissance = convertToLocalDateViaInstant(dateNaissance);
                                LocalDate localeDateJour = convertToLocalDateViaInstant(dateJour);
                                int resultatAge = Period.between(localeDateNaissance, localeDateJour).getYears();

                                HumanByFirestationNumber humanByFirestationNumber = new HumanByFirestationNumber();
                                humanByFirestationNumber.setFirestationNumber(firestation.getStation());
                                humanByFirestationNumber.setLastName(person.getLastName());
                                humanByFirestationNumber.setPhone(person.getPhone());
                                humanByFirestationNumber.setMedications(medicalrecord.getMedications());
                                humanByFirestationNumber.setAllergies(medicalrecord.getAllergies());
                                humanByFirestationNumber.setAge(String.valueOf(resultatAge));
                                humanByFirestationNumberPersistence.getHumanByFirestationNumberPersistences().add(humanByFirestationNumber);
                            }
                        }

                    }
                }

            }
        }
        return humanByFirestationNumberPersistence;
    }
    /**
     * Obtenir dans la zone que dessert une caserne toutes les adresses soumise à cette zone.
     * Pour chaque adresse, est associé la liste de personnes y vivant.
     * @param firestationNumber L'identifiant de la caserne.
     * @return Liste des personnes par adresse.
     * @throws ParseException Dans le cas ou la date de naissance est au mauvais format.
     */
    public FloodStationPersonPersistance obtainPersonByAddress(String firestationNumber) throws ParseException {
        List<Person> personList = alertPersistance.getPersons();
        List<Firestation> firestationList = alertPersistance.getFirestations();
        List<Medicalrecord> medicalrecordList = alertPersistance.getMedicalrecords();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        GregorianCalendar calendar = new GregorianCalendar();
        floodStationPersistance.getPersonByAddress().clear();

        for (Firestation firestation : firestationList) {
            if (firestation.getStation().equalsIgnoreCase(firestationNumber)) {
                for (Person person : personList) {
                    if (person.getAddress().equalsIgnoreCase(firestation.getAddress())) {
                        for (Medicalrecord medicalrecord : medicalrecordList) {
                            if (medicalrecord.getFirstName().equalsIgnoreCase(person.getFirstName())
                                    && medicalrecord.getLastName().equalsIgnoreCase(person.getLastName())) {
                                Date dateNaissance = format.parse(medicalrecord.getBirthdate());
                                Date dateJour = new Date(System.currentTimeMillis());
                                calendar.setTimeInMillis(dateNaissance.getTime());
                                calendar.add(Calendar.YEAR, 19);
                                LocalDate localeDateNaissance = convertToLocalDateViaInstant(dateNaissance);
                                LocalDate localeDateJour = convertToLocalDateViaInstant(dateJour);
                                int resultatAge = Period.between(localeDateNaissance, localeDateJour).getYears();

                                FloodStationPerson floodStation = new FloodStationPerson();
                                floodStation.setLastName(person.getLastName());
                                floodStation.setPhone(person.getPhone());
                                floodStation.setAge(String.valueOf(resultatAge));
                                floodStation.setMedications(medicalrecord.getMedications());
                                floodStation.setAllergies(medicalrecord.getAllergies());

                                if (floodStationPersistance.getPersonByAddress().get(firestation.getAddress()) == null) {
                                    floodStationPersistance.getPersonByAddress().put(firestation.getAddress(), new ArrayList<>());
                                }
                                floodStationPersistance.getPersonByAddress().get(firestation.getAddress()).add(floodStation);
                            }
                        }
                    }
                }
            }
        }
        return floodStationPersistance;
    }
    /**
     * Renvoie des informations de la totalité des personnes dans la base de donnée.
     * @return La liste des personnes avec leurs informations : (Nom, téléphone, âge, médicamentation, allérgies)
     * @throws ParseException Dans le cas ou la date de naissance est au mauvais format.
     */
    public List<AllPersonList> obtainAllPersonList() throws ParseException {
        List<Person> personList = alertPersistance.getPersons();
        List<Medicalrecord> medicalrecordList = alertPersistance.getMedicalrecords();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        GregorianCalendar calendar = new GregorianCalendar();

        List<AllPersonList> allPersonLists = new ArrayList<>();

        for (Person person : personList){
            for (Medicalrecord medicalrecord : medicalrecordList){
                if (medicalrecord.getFirstName().equalsIgnoreCase(person.getFirstName())
                        && medicalrecord.getLastName().equalsIgnoreCase(person.getLastName())) {
                    Date dateNaissance = format.parse(medicalrecord.getBirthdate());
                    Date dateJour = new Date(System.currentTimeMillis());
                    calendar.setTimeInMillis(dateNaissance.getTime());
                    calendar.add(Calendar.YEAR, 19);
                    LocalDate localeDateNaissance = convertToLocalDateViaInstant(dateNaissance);
                    LocalDate localeDateJour = convertToLocalDateViaInstant(dateJour);
                    int resultatAge = Period.between(localeDateNaissance, localeDateJour).getYears();

                    AllPersonList allPersonList = new AllPersonList();
                    allPersonList.setLastName(person.getLastName());
                    allPersonList.setAddress(person.getAddress());
                    allPersonList.setAge(String.valueOf(resultatAge));
                    allPersonList.setEmail(person.getEmail());
                    allPersonList.setMedications(medicalrecord.getMedications());
                    allPersonList.setAllergies(medicalrecord.getAllergies());

                    allPersonLists.add(allPersonList);
                }
            }
        }
        return allPersonLists;
    }
    /**
     * Revoie la liste des emails de tout les habitants de la ville.
     * @param city L'identifient de la ville.
     * @return La liste d'adresse email de tout les habitants de la ville.
     */
    public List<String> haveEmailsFromAllPersons(String city) {
        List<Person> personList = alertPersistance.getPersons();
        List<String> emailAllPerson = new ArrayList<>();

        for (Person person : personList) {
            if (person.getCity() == city) {
                emailAllPerson.add(person.getEmail());
            }
        }
        return emailAllPerson;
    }
    /**
     * Renvoie une liste des personnes couvertes par la caserne de pompiers correspondante.
     * Renvoie le décompte du nombre d'enfants et du nombre d'adultes.
     * @param stationNumber L'identifiant de la caserne.
     * @return Liste des personnes couvertes par la caserne avec : (prénom, nom, adresse, téléphone). Et le nombre d'enfants et d'adultes.
     * @throws ParseException Dans le cas ou la date de naissance est au mauvais format.
     */
    public PersonByFirestaionNumberPersistance obtaintListPersonByFireStation(String stationNumber) throws ParseException {
        List<Person> personList = alertPersistance.getPersons();
        List<Firestation> firestationList = alertPersistance.getFirestations();
        List<Medicalrecord> medicalrecordList = alertPersistance.getMedicalrecords();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        GregorianCalendar calendar = new GregorianCalendar();
        personByFirestaionNumberPersistance.getPersonByFirestationNumbers().clear();
        personByFirestaionNumberPersistance.setAdultNumber(0);
        personByFirestaionNumberPersistance.setChildNumber(0);

        for (Firestation firestation : firestationList){
            if (firestation.getStation().equalsIgnoreCase(stationNumber)){
                for (Person person : personList){
                    if (person.getAddress().equalsIgnoreCase(firestation.getAddress())){
                        for (Medicalrecord medicalrecord : medicalrecordList){
                            if (person.getFirstName().equalsIgnoreCase(medicalrecord.getFirstName())
                            && person.getLastName().equalsIgnoreCase(medicalrecord.getLastName())){
                                Date dateNaissance = format.parse(medicalrecord.getBirthdate());
                                Date dateJour = new Date(System.currentTimeMillis());
                                calendar.setTimeInMillis(dateNaissance.getTime());
                                calendar.add(Calendar.YEAR, 19);
                                LocalDate localeDateNaissance = convertToLocalDateViaInstant(dateNaissance);
                                LocalDate localeDateJour = convertToLocalDateViaInstant(dateJour);
                                int resultatAge = Period.between(localeDateNaissance, localeDateJour).getYears();

                                PersonByFirestationNumber personByFirestationNumber = new PersonByFirestationNumber();
                                personByFirestationNumber.setFirstName(person.getFirstName());
                                personByFirestationNumber.setLastName(person.getLastName());
                                personByFirestationNumber.setAddress(person.getAddress());
                                personByFirestationNumber.setPhone(person.getPhone());

                                if (resultatAge <= 18){
                                    personByFirestaionNumberPersistance.setChildNumber((personByFirestaionNumberPersistance.getChildNumber())+1);
                                } else {
                                    personByFirestaionNumberPersistance.setAdultNumber((personByFirestaionNumberPersistance.getAdultNumber())+1);
                                }
                                personByFirestaionNumberPersistance.getPersonByFirestationNumbers().add(personByFirestationNumber);
                            }
                        }
                    }
                }
            }
        }
        return personByFirestaionNumberPersistance;
    }
}

