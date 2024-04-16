package de.dhbw.ase.view;

import de.dhbw.ase.dao.PersonDAO;
import de.dhbw.ase.daoimpl.PersonDAOImpl;
import de.dhbw.ase.model.Person;
import de.dhbw.ase.restHelperService.PersonRestHelperService;
import de.dhbw.ase.service.PersonService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class PersonView {

    Scanner scanner = new Scanner(System.in);

//    PersonRestService personRestService = new PersonRestService();


    PersonDAO personDAO = new PersonDAOImpl();
    PersonService personService = new PersonService(personDAO);
    PersonRestHelperService personRestService = new PersonRestHelperService(personService);

    public void defaultView() {

        int input;

        consoleMenu();


        boolean done = false;
        while (!done) {

            input = scanner.nextInt();
            switch (input) {
                case 1:
                    getAllPersons();
                    consoleMenu();
                    break;
                case 2:
                    System.out.println("Geben Sie die ID der Person ein, die angezeigt werden soll: ");
                    personRestService.getPerson(scanner.nextInt());
                    break;
                case 3:
                    addPerson();
                    break;
                case 4:
                    changePerson();
                    break;
                case 5:
                    deletePerson();
                    break;
                case 6:
                    followPerson();
                    break;
                case 7:
                    addAddress();
                    break;
                case 8:
                    deleteAddress();
                    break;
                case 9:
                    addPhoneNumber();
                    break;
                case 10:
                    deletePhoneNumber();
                    break;
                case 11:
                    done = true;
                    break;
                default:
                    scanner.close();
                    break;
            }
        }

    }

    private static void consoleMenu() {
        System.out.println("Welche Operation wollen Sie ausführen? Tippen Sie dazu die Nummer ein.");
        System.out.println("1. Alle Personen anzeigen");
        System.out.println("2. Eine Person anzeigen (ID benötigt)");
        System.out.println("3. Eine Person hinzufügen");
        System.out.println("4. Eine Person aktualisieren");
        System.out.println("5. Eine Person löschen");
        System.out.println("6. Einer Person folgen");
        System.out.println("7. Adresse zu Person hinzufügen");
        System.out.println("8. Adresse von Person entfernen");
        System.out.println("9. Telefonnummer zu Person hinzufügen");
        System.out.println("10. Telefonnummer von Person entfernen");
    }

    private void getAllPersons() {

        List<Person> persons = personRestService.getAllPersons().stream().toList();

        String leftAlignFormat = "| %-5d | %-15s | %-15s |%n";

        System.out.format("+-------+-----------------+-----------------+%n");

        System.out.format("| ID    | First Name      | Last Name       |%n");
        System.out.format("+-------+-----------------+-----------------+%n");

        for (Person p : persons) {

            System.out.format(leftAlignFormat, p.getId(), p.getFirstName(), p.getLastName());
        }
        System.out.format("+-------+-----------------+-----------------+%n");


//                personRestService.getAllPersons();
    }

    private void deletePhoneNumber() {
        System.out.print("Bitte geben Sie die ID der Person an, von welcher Sie eine Telefonnummer löschen möchten: ");
        int personID = scanner.nextInt();
        System.out.println();
        System.out.print("Bitte geben Sie die ID der Telefonnummer an, um sie zu entfernen");
        int phoneNumberID = scanner.nextInt();
        personRestService.deletePhoneNumber(personID, phoneNumberID);
        scanner.close();

    }

    private void addPhoneNumber() {
        System.out.print("Bitte geben Sie die ID der Person an, zu der Sie eine Telefonnummer hinzufügen wollen: ");
        int personID = scanner.nextInt();
        System.out.println();
        System.out.print("Bitte geben Sie die ID der Telefonnummer an, um Sie hinzuzufügen");
        int phoneNumberID = scanner.nextInt();
        personRestService.addPhoneNumber(personID, phoneNumberID);
        scanner.close();

    }

    private void deleteAddress() {
        System.out.print("Bitte geben Sie die ID der Person an, von welcher Sie eine Adresse löschen möchten: ");
        int personID = scanner.nextInt();
        System.out.println();
        System.out.print("Bitte geben Sie die ID der Adresse an, um sie zu entfernen");
        int addressID = scanner.nextInt();
        personRestService.deleteAddress(personID, addressID);
        scanner.close();
    }

    private void addAddress() {
        System.out.print("Bitte geben Sie die ID der Person an, zu der Sie eine Adresse hinzufügen wollen: ");
        int personID = scanner.nextInt();
        System.out.println();
        System.out.print("Bitte geben Sie die ID der Adresse an, um Sie hinzuzufügen");
        int addressID = scanner.nextInt();
        personRestService.addAddress(personID, addressID);
        scanner.close();
    }

    private void followPerson() {
        System.out.println("Bitte geben Sie die ID der Person ein, der Sie folgen möchten: ");
        int id = scanner.nextInt();
        personRestService.followPerson(id);
        scanner.close();
    }

    private void deletePerson() {

        System.out.println("Geben Sie die ID der Person ein, die gelöscht werden soll: ");
        int id = scanner.nextInt();
        personRestService.deletePerson(id);
        scanner.close();

    }

    private void changePerson() {

        System.out.println("Bitte geben Sie die ID der Person ein, die Sie ändern möchten: ");
        int id = scanner.nextInt();
        Person person = datenEingabe();
        personRestService.updatePerson(id, person);
    }


    private Person datenEingabe() {
        long id = 0;
        String firstName;
        String lastName;
        int day;
        int month;
        int year;

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

//        scanner.close();

        return new Person(id, firstName, lastName, day, month, year, null, null);
    }

    private void addPerson() {


        System.out.println("Bitte geben Sie die folgenden Informationen der Person ein, welche Sie hinzufügen wollen:");

        Person person = datenEingabe();

        personRestService.addPerson(person);

    }
}
