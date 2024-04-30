package de.dhbw.ase.view;

import de.dhbw.ase.model.Address;
import de.dhbw.ase.model.Person;
import de.dhbw.ase.restHelperService.AddressRestHelperService;
import de.dhbw.ase.restHelperService.LoginRestHelperService;
import de.dhbw.ase.restHelperService.PersonRestHelperService;
import de.dhbw.ase.restHelperService.PhoneNumberRestHelperService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersonView implements View {

    Scanner scanner = new Scanner(System.in);


    private final PersonRestHelperService personRestService;
    private final AddressRestHelperService addressRestService;
    private final PhoneNumberRestHelperService phoneNumberRestService;
    private final LoginRestHelperService loginRestHelperService;
    private final String inputPersonID = "Bitte geben Sie die ID der Person ein";


    public PersonView(PersonRestHelperService personRestService, AddressRestHelperService addressRestService, PhoneNumberRestHelperService phoneNumberRestService, LoginRestHelperService loginService) {
        this.personRestService = personRestService;
        this.addressRestService = addressRestService;
        this.phoneNumberRestService = phoneNumberRestService;
        this.loginRestHelperService = loginService;
    }

    public void defaultView() {

        int input;
        boolean done = false;
        while (!done) {

            consoleMenu();

            input = scanner.nextInt();
            switch (input) {
                case 1:
                    getAllPersons();
                    //TODO
//                    System.out.println("eingeloggte Person: " + loginService.getLoggedInUser().toString());
                    break;
                case 2:
                    System.out.println(inputPersonID + ", die angezeigt werden soll: ");
                    System.out.println(personRestService.getPerson(scanner.nextInt()).toString());
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
                    unFollowPerson();
                    break;
                case 8:
                    addAddress();
                    break;
                case 9:
                    removeAddress();
                    break;
                case 10:
                    addPhoneNumber();
                    break;
                case 11:
                    deletePhoneNumber();
                    break;
                case 12:
                    done = true;
                    break;
                default:
                    done = true;
                    scanner.close();
                    break;
            }
        }

        new MainView(personRestService, addressRestService, phoneNumberRestService, loginRestHelperService).defaultView();

    }


    public void consoleMenu() {
        System.out.println();
        System.out.println("---------------------------------------------------------------------");
        System.out.println();
        System.out.println("Welche Operation wollen Sie ausführen? Tippen Sie dazu die Nummer ein und bestätigen mit Enter.");
        System.out.println("1. Alle Personen anzeigen");
        System.out.println("2. Eine Person anzeigen (ID benötigt)");
        System.out.println("3. Eine Person hinzufügen");
        System.out.println("4. Eine Person aktualisieren");
        System.out.println("5. Eine Person löschen");
        System.out.println("6. Einer Person folgen");
        System.out.println("7. Einer Person entfolgen");
        System.out.println("8. Adresse zu Person hinzufügen");
        System.out.println("9. Adresse von Person entfernen");
        System.out.println("10. Telefonnummer zu Person hinzufügen");
        System.out.println("11. Telefonnummer von Person entfernen");
        System.out.println("12. Zurück zum Hauptmenü");
    }

    private void getAllPersons() {

        List<Person> persons = personRestService.getAllPersons();

        String leftAlignFormat = "| %-5d | %-15s | %-15s | %-15s | %-90s | %-41s | %-20s |%n";
        System.out.format("+-------+-----------------+-----------------+-----------------+--------------------------------------------------------------------------------------------+-------------------------------------------+----------------------+%n");

        System.out.format("| ID    | Vorname         | Nachname        | Geburtsdatum    | Adresse(n)                                                                                 | Telefonnummer(n)                          | Gefolgt              |%n");
        System.out.format("+-------+-----------------+-----------------+-----------------+--------------------------------------------------------------------------------------------+-------------------------------------------+----------------------+%n");

        for (Person p : persons) {
            List<Person> following = p.getFollowing();

            List<String> followingIds = new ArrayList<>();
            for (Person person : following) {
                System.out.println("Person zum hinzufügen: " + person.getFirstName());
                followingIds.add(person.getId() + "");
            }
            System.out.format(leftAlignFormat, p.getId(), p.getFirstName(), p.getLastName(), p.getDateOfBirth(), p.getAddresses(), p.getPhoneNumbers(), followingIds);
        }
        System.out.format("+-------+-----------------+-----------------+-----------------+--------------------------------------------------------------------------------------------+-------------------------------------------+----------------------+%n");


//                personRestService.getAllPersons();
    }

    private void deletePhoneNumber() {
        System.out.print(inputPersonID + ", von welcher Sie eine Telefonnummer löschen möchten: ");
        long personID = scanner.nextInt();
        System.out.println();
        System.out.print("Bitte geben Sie die ID der Telefonnummer an, um sie zu entfernen: ");

        personRestService.removePhoneNumber(personID, phoneNumberRestService.getPhoneNumber(scanner.nextLong()));

    }

    private void addPhoneNumber() {
        System.out.print(inputPersonID + ", zu der Sie eine Telefonnummer hinzufügen wollen: ");
        long personID = scanner.nextInt();
        System.out.println();
        System.out.print("Bitte geben Sie die ID der Telefonnummer an, um Sie hinzuzufügen: ");
        personRestService.addPhoneNumber(personID, phoneNumberRestService.getPhoneNumber(scanner.nextLong()));
    }

    private void removeAddress() {
        System.out.print(inputPersonID + ", von welcher Sie eine Adresse löschen möchten: ");
        long personID = scanner.nextInt();
        System.out.println();
        System.out.print("Bitte geben Sie die ID der Adresse an, um sie zu entfernen: ");
        Address address = addressRestService.getAddress(scanner.nextLong());
        personRestService.removeAddress(personID, address);
    }

    private void addAddress() {

        System.out.print(inputPersonID + ", zu der Sie eine Adresse hinzufügen wollen: ");
        long personID = scanner.nextLong();
        System.out.println();

        System.out.print("Bitte geben Sie die ID der Adresse an, um Sie hinzuzufügen: ");

        Address address = addressRestService.getAddress(scanner.nextLong());

        personRestService.addAddress(personID, address);
    }

    private void followPerson() {
        System.out.println(inputPersonID + ", der Sie folgen möchten: ");
        int id = scanner.nextInt();

        personRestService.followPerson(loginRestHelperService.getLoggedInUser().getId(), id);
    }


    private void unFollowPerson() {
        System.out.println(inputPersonID + ", der Sie nicht mehr folgen möchten: ");
        int id = scanner.nextInt();
        personRestService.unFollowPerson(loginRestHelperService.getLoggedInUser().getId(), id);
    }

    private void deletePerson() {

        System.out.println(inputPersonID + ", die gelöscht werden soll: ");
        int id = scanner.nextInt();
        personRestService.deletePerson(id);
    }

    private void changePerson() {

        System.out.println(inputPersonID + ", die Sie ändern möchten: ");
        long id = scanner.nextInt();
        Person person = dataInput();
        person.setId(id);

        //TODO vllt hier Logik einbauen, damit die ID direkt geprüft wird (Logik und Anzeige mischen?)
        personRestService.updatePerson(person, loginRestHelperService.getLoggedInUser().getId());
    }


    private Person dataInput() {

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

        System.out.println("Sie können später eine bestehende Adresse oder Telefonnummer hinzufügen oder ganz neue Einträge erstellen");

        return new Person(id, firstName, lastName, day, month, year, null, null);

    }


    private void addPerson() {

        System.out.println("Bitte geben Sie die folgenden Informationen der Person ein, welche Sie hinzufügen wollen:");

        personRestService.addPerson(dataInput());
    }
}
