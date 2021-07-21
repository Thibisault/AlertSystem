package com.SafetyNet.alerts;


import com.SafetyNet.alerts.model.Firestation;
import com.SafetyNet.alerts.model.Medicalrecord;
import com.SafetyNet.alerts.model.Person;
import com.SafetyNet.alerts.repository.AlertPersistance;
import com.SafetyNet.alerts.service.AlertService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

@Data
@AllArgsConstructor
@Component
public class ObtainRandomPersistance {


    @Autowired
    private AlertPersistance alertPersistanceTest;

    @Autowired
    private AlertService alertServiceTest;

    private Random random = new Random(System.currentTimeMillis());

    List<Person> personList = new ArrayList<>();
    List<Firestation> firestationList = new ArrayList<>();
    List<Medicalrecord> medicalrecordList = new ArrayList<>();

    public ObtainRandomPersistance() {
        super();
    }

    private LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Calculer l'âge d'un dosser médical du format : '00/00/0000' en années : '00'.
     *
     * @param dossierMedical
     * @return L'âge.
     * @throws ParseException
     */
    private int calculerAge(Medicalrecord dossierMedical) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        GregorianCalendar calendar = new GregorianCalendar();
        Date dateNaissance = format.parse(dossierMedical.getBirthdate());
        Date dateJour = new Date(System.currentTimeMillis());
        calendar.setTimeInMillis(dateNaissance.getTime());
        calendar.add(Calendar.YEAR, 19);
        LocalDate localeDateNaissance = convertToLocalDateViaInstant(dateNaissance);
        LocalDate localeDateJour = convertToLocalDateViaInstant(dateJour);
        int resultatAge = Period.between(localeDateNaissance, localeDateJour).getYears();
        return resultatAge;
    }

    private static String getRandomStr(int n) {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        StringBuilder s = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int) (str.length() * Math.random());
            s.append(str.charAt(index));
        }
        return s.toString();
    }

    public AlertPersistance obtainRandomAlertPersistance(int numberObjectInList) {

        String chaineTemporaire = "";

        for (int y = 0; y < numberObjectInList; y++) {
            Person person = new Person();
            Firestation firestation = new Firestation();
            Medicalrecord medicalrecord = new Medicalrecord();

            for (int i = 0; i < random.nextInt(4) + 3; i++) {
                chaineTemporaire = chaineTemporaire + getRandomStr(1);
            }
            person.setFirstName(chaineTemporaire);
            medicalrecord.setFirstName(chaineTemporaire);
            chaineTemporaire = "";

            for (int i = 0; i < random.nextInt(4) + 3; i++) {
                chaineTemporaire = chaineTemporaire + getRandomStr(1);
            }
            person.setLastName(chaineTemporaire);
            medicalrecord.setLastName(chaineTemporaire);
            chaineTemporaire = "";


            if (personList.size() == 0 || personList.size() == 1) {
                for (int i = 0; i < random.nextInt(4) + 2; i++) {
                    chaineTemporaire = chaineTemporaire + getRandomStr(1);
                }
                person.setAddress(chaineTemporaire);
            } else if (personList.get(y - 1).getAddress().equalsIgnoreCase(personList.get(y - 2).getAddress())) {
                for (int i = 0; i < random.nextInt(4) + 2; i++) {
                    chaineTemporaire = chaineTemporaire + getRandomStr(1);
                }
                person.setAddress(chaineTemporaire);
            } else {
                person.setAddress(personList.get(y - 1).getAddress());
            }

            if (firestationList.size() < (numberObjectInList/2)) {
                if (firestationList.size() == 0 || firestationList.size() == 1) {
                    firestation.setAddress(person.getAddress());
                    firestation.setStation("1");
                    firestationList.add(firestation);
                } else if (person.getAddress() != (personList.get(y - 1).getAddress())) {
                    firestation.setAddress(person.getAddress());
                    firestation.setStation(String.valueOf(y));
                    firestationList.add(firestation);
                }
            }
                chaineTemporaire = "";

            for (int i = 0; i < random.nextInt(4) + 2; i++) {
                chaineTemporaire = chaineTemporaire + getRandomStr(1);
            }
            person.setCity(chaineTemporaire);
            chaineTemporaire = "";

            for (int i = 0; i < random.nextInt(4) + 2; i++) {
                chaineTemporaire = chaineTemporaire + getRandomStr(1);
            }
            person.setZip(chaineTemporaire);
            chaineTemporaire = "";

            for (int i = 0; i < random.nextInt(4) + 2; i++) {
                chaineTemporaire = chaineTemporaire + getRandomStr(1);
            }
            person.setPhone(chaineTemporaire);
            chaineTemporaire = "";

            for (int i = 0; i < random.nextInt(4) + 2; i++) {
                chaineTemporaire = chaineTemporaire + getRandomStr(1);
            }
            person.setEmail(chaineTemporaire);
            chaineTemporaire = "";

            int age =0;
            if (medicalrecordList.size() > 0) {
                try {
                    age = calculerAge(medicalrecordList.get(y - 1));
                } catch (ParseException e) {
                    System.out.println("La liste des dossiers médicales était vide avant le calcul d'âge");
                    e.printStackTrace();
                }
            }

            if (medicalrecordList.size() == 0){
                chaineTemporaire = chaineTemporaire +
                String.valueOf(random.nextInt(1)) + String.valueOf(random.nextInt(8) + 1) + "/" +
                        String.valueOf(random.nextInt(2)) + String.valueOf(random.nextInt(8) + 1) + "/" +
                        "1" + "9" + String.valueOf(random.nextInt(3) + 6) + String.valueOf(random.nextInt(9));
            } else if (age < 18){
                chaineTemporaire = chaineTemporaire +
                        String.valueOf(random.nextInt(1)) + String.valueOf(random.nextInt(8) + 1) + "/" +
                        String.valueOf(random.nextInt(2)) + String.valueOf(random.nextInt(8) + 1) + "/" +
                        "1" + "9" + String.valueOf(random.nextInt(3) + 6) + String.valueOf(random.nextInt(9));
            } else {
                chaineTemporaire = chaineTemporaire +
                        String.valueOf(random.nextInt(1)) + String.valueOf(random.nextInt(8) + 1) + "/" +
                        String.valueOf(random.nextInt(2)) + String.valueOf(random.nextInt(8) + 1) + "/" +
                        "2" + "0" + "1" + String.valueOf(random.nextInt(9));
            }
            medicalrecord.setBirthdate(chaineTemporaire);
            chaineTemporaire = "";

            for (int z = 0; z < random.nextInt(2); z++) {
                for (int i = 0; i < random.nextInt(5) + 3; i++) {
                    chaineTemporaire = chaineTemporaire + getRandomStr(1);
                }
                chaineTemporaire = chaineTemporaire + " : ";
                for (int i = 0; i < random.nextInt(3) + 1; i++) {
                    chaineTemporaire = chaineTemporaire + random.nextInt(6) + 3;
                }
                chaineTemporaire = chaineTemporaire + "mg";
                medicalrecord.setMedications(new String[]{chaineTemporaire});
                chaineTemporaire = "";
            }
            chaineTemporaire = "";

            for (int z = 0; z < random.nextInt(2); z++) {
                for (int i = 0; i < random.nextInt(5) + 3; i++) {
                    chaineTemporaire = chaineTemporaire + getRandomStr(1);
                    medicalrecord.setAllergies(new String[]{chaineTemporaire});
                }
                chaineTemporaire = "";
            }
            personList.add(person);
            medicalrecordList.add(medicalrecord);
        }
        AlertPersistance nouvelleAlerte = new AlertPersistance();
        nouvelleAlerte.setPersons(personList);
        nouvelleAlerte.setFirestations(firestationList);
        nouvelleAlerte.setMedicalrecords(medicalrecordList);
        return nouvelleAlerte;
    }
}
