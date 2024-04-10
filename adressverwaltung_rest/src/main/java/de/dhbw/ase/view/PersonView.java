package de.dhbw.ase.view;

import de.dhbw.ase.model.Person;
import de.dhbw.ase.restService.PersonRestService;

import java.time.LocalDate;
import java.util.Scanner;

public class PersonView {

    Scanner scanner = new Scanner(System.in);

    PersonRestService personRestService = new PersonRestService();

    public void defaultView() {

        int input;

        System.out.println("Welche Operation wollen Sie ausführen? Tippen Sie dazu die Nummer ein.");
        System.out.println("1. Alle Personen anzeigen");
        System.out.println("2. Eine Person anzeigen (ID benötigt)");
        System.out.println("3. Eine Person hinzufügen");
        System.out.println("4. Eine Person aktualisieren");
        System.out.println("5. Eine Person löschen");
        System.out.println("6. Einer Person folgen");


        input = scanner.nextInt();

        switch (input) {
            case 1:
                personRestService.getAllPersons();

            case 2:
                System.out.println("Geben Sie die ID der Person ein, die angezeigt werden soll: ");
                personRestService.getPerson(scanner.nextInt());

            case 3:
                addPerson();

            case 4:
                changePerson();

            case 5:
                deletePerson();

            case 6:
                followPerson();

            default:
                break;
        }

    }

    private void followPerson() {
        System.out.println("Bitte geben Sie die ID der Person ein, der Sie folgen möchten: ");
        int id = scanner.nextInt();
        personRestService.followPerson(id);
    }

    private void deletePerson() {

        System.out.println("Geben Sie die ID der Person ein, die gelöscht werden soll: ");
        int id = scanner.nextInt();
        personRestService.deletePerson(id);

    }

    private void changePerson() {
        System.out.println("Was wollen Sie an der Person ändern?");
        System.out.println("1. Daten");
        System.out.println("2. Adressen");
        System.out.println("3. Telefonnummern");

        int input = scanner.nextInt();
        switch (input) {
            case 1:
                System.out.println("Bitte geben Sie die ID der Person ein, die Sie ändern möchten: ");
                int id = scanner.nextInt();
                Person person = datenEingabe();
                personRestService.updatePerson(id, person);
        }
    }

    private Person datenEingabe() {

        String firstName;
        String lastName;
        int day;
        int month;
        int year;


        //TODO wird geskippt
        System.out.print("Vorname: ");
        firstName = scanner.next();
        System.out.println();
        System.out.print("Nachname: ");
        lastName = scanner.next();
        System.out.println();
        System.out.println("Geburtsdatum : ");
        System.out.print("Tag:");
        day = scanner.nextInt();
        System.out.println();
        System.out.print("Monat (1-12): ");
        month = scanner.nextInt();
        System.out.println();
        System.out.print("Jahr (z.B. 1990): ");

        year = scanner.nextInt();

        while (year < 1900 || year > LocalDate.now().getYear()) {
            System.out.println("Falsches Jahr! Bitte geben Sie ihr Geburtsjahr erneut ein: ");
            year = scanner.nextInt();
        }

        return new Person(firstName, lastName, day, month, year, null, null);
    }

    private void addPerson() {


        System.out.println("Bitte geben Sie die folgenden Informationen der Person ein, welche Sie hinzufügen wollen:");

        Person person = datenEingabe();

        personRestService.addPerson(person);

    }


}
