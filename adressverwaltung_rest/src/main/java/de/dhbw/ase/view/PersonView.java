package de.dhbw.ase.view;

import de.dhbw.ase.model.Address;
import de.dhbw.ase.model.Person;
import de.dhbw.ase.model.PhoneNumber;
import de.dhbw.ase.model.singleton.LoggedInPersonSingleton;
import de.dhbw.ase.restHelperService.AddressRestHelperService;
import de.dhbw.ase.restHelperService.LoginRestHelperService;
import de.dhbw.ase.restHelperService.PersonRestHelperService;
import de.dhbw.ase.restHelperService.PhoneNumberRestHelperService;
import de.dhbw.ase.utils.PersonSorter;

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
            scanner.nextLine();
            switch (input) {
                case 1:
                    getAllPersons();
                    break;
                case 2:
                    getAllPersonsSorted();
                    break;
                case 3:
                    getPerson();
                    break;
                case 4:
                    addPerson();
                    break;
                case 5:
                    changePerson();
                    break;
                case 6:
                    deletePerson();
                    break;
                case 7:
                    followPerson();
                    break;
                case 8:
                    unFollowPerson();
                    break;
                case 9:
                    addAddress();
                    break;
                case 10:
                    removeAddress();
                    break;
                case 11:
                    addPhoneNumber();
                    break;
                case 12:
                    deletePhoneNumber();
                    break;
                case 13:
                    done = true;
                    break;
                default:
                    System.out.println("Falsche Eingabe!");
                    defaultView();
                    break;
            }
        }

        new MainView(personRestService, addressRestService, phoneNumberRestService, loginRestHelperService).defaultView();

    }

    private void getPerson() {
        System.out.println(inputPersonID + ", die angezeigt werden soll: ");
        formatting(List.of(personRestService.getPerson(scanner.nextInt())));
    }


    private void getAllPersonsSorted() {

        System.out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        System.out.println("Welche Operation wollen Sie ausführen? Tippen Sie dazu die Nummer ein und bestätigen mit Enter.");
        System.out.println("1. Nach Vornamen sortiert ausgeben");
        System.out.println("2. Nach Nachnamen sortiert ausgeben");
        System.out.println("3. Nach Alter sortiert ausgeben (Jüngste zuerst)");
        System.out.println("4. Nach Alter sortiert ausgeben (Älteste zuerst)");
        System.out.println("5. Zurück zum Personenmenü");

        PersonSorter personSorter = new PersonSorter();

        int input = scanner.nextInt();
        scanner.nextLine();
        switch (input) {
            case 1:
                formatting(personSorter.sortByFirstName((personRestService.getAllPersons())));
                break;
            case 2:
                formatting(personSorter.sortByLastName((personRestService.getAllPersons())));
                break;
            case 3:
                formatting(personSorter.sortByAgeYoungest((personRestService.getAllPersons())));
                break;
            case 4:
                formatting(personSorter.sortByAgeOldest((personRestService.getAllPersons())));
            default:
                defaultView();
                break;


        }

    }


    public void consoleMenu() {
        System.out.println("\n--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        System.out.println("Welche Operation wollen Sie ausführen? Tippen Sie dazu die Nummer ein und bestätigen mit Enter.");
        System.out.println("1. Alle Personen anzeigen");
        System.out.println("2. Alle Personen sortiert ausgeben");
        System.out.println("3. Eine Person anzeigen (ID benötigt)");
        System.out.println("4. Eine Person hinzufügen");
        System.out.println("5. Eine Person aktualisieren");
        System.out.println("6. Eine Person löschen");
        System.out.println("7. Einer Person folgen");
        System.out.println("8. Einer Person entfolgen");
        System.out.println("9. Adresse zu Person hinzufügen");
        System.out.println("10. Adresse von Person entfernen");
        System.out.println("11. Telefonnummer zu Person hinzufügen");
        System.out.println("12. Telefonnummer von Person entfernen");
        System.out.println("13. Zurück zum Hauptmenü");
    }

    private void getAllPersons() {

        List<Person> persons = personRestService.getAllPersons();

        formatting(persons);

//                personRestService.getAllPersons();
    }

    private static void formatting(List<Person> persons) {
        String leftAlignFormat = "| %-5d | %-15s | %-15s | %-15s | %-85s | %-40s | %-9s |%n";
        System.out.format("+-------+-----------------+-----------------+-----------------+---------------------------------------------------------------------------------------+------------------------------------------+-----------+%n");
        System.out.format("| ID    | Vorname         | Nachname        | Geburtsdatum    | Adresse(n)                                                                            | Telefonnummer(n)                         | Gefolgt   |%n");
        System.out.format("+-------+-----------------+-----------------+-----------------+---------------------------------------------------------------------------------------+------------------------------------------+-----------+%n");

        for (Person p : persons) {
            List<Person> following = p.getFollowing();

            List<String> followingIds = new ArrayList<>();
            for (Person person : following) {
                followingIds.add(person.getId() + "");
            }
            System.out.format(leftAlignFormat, p.getId(), p.getFirstName(), p.getLastName(), p.getDateOfBirth().getBirthday(), p.getAddresses(), p.getPhoneNumbers(), followingIds);
        }
        System.out.format("+-------+-----------------+-----------------+-----------------+---------------------------------------------------------------------------------------+------------------------------------------+-----------+%n");
    }

    private void deletePhoneNumber() {
        System.out.print(inputPersonID + ", von welcher Sie eine Telefonnummer löschen möchten: ");
        //TODO id schauen
        long personID = scanner.nextLong();
        scanner.nextLine();

        System.out.println();
        System.out.print("Bitte geben Sie die ID der Telefonnummer an, um sie zu entfernen: ");
        String phoneNumber = scanner.nextLine();
        System.out.println("ist mobil?");
        boolean isMobile = scanner.nextLine().equalsIgnoreCase("Ja");
        PhoneNumber phoneNumber1 = new PhoneNumber(phoneNumber, isMobile);
        if (phoneNumberRestService.getAllPhoneNumbers().stream().anyMatch(p -> p.getNumber().equals(phoneNumber) && p.isMobile() == isMobile)) {
            personRestService.removePhoneNumber(personID, phoneNumber1);
        } else {
            System.out.println("Diese Nummer ist nicht mit der Person verknüpft!");

        }
    }

    private void addPhoneNumber() {

        System.out.print(inputPersonID + ", zu der Sie eine Telefonnummer hinzufügen wollen: ");
        //TODO id schauen
        long personID = scanner.nextLong();
        scanner.nextLine();

        System.out.println();
        System.out.print("Bitte geben Sie die ID der Telefonnummer an, um Sie hinzuzufügen: ");

        String phoneNumber = scanner.nextLine();
        System.out.println("ist mobil?");
        boolean isMobile = scanner.nextLine().equalsIgnoreCase("Ja");
        PhoneNumber phoneNumber1 = new PhoneNumber(phoneNumber, isMobile);
        if (phoneNumberRestService.getAllPhoneNumbers().stream().noneMatch(p -> p.equals(phoneNumber1))) {
            phoneNumberRestService.addPhoneNumber(phoneNumber1);
        } else {
        }
        personRestService.addPhoneNumber(personID, phoneNumber1);


//        personRestService.addPhoneNumber(personID, phoneNumberRestService.getPhoneNumber(scanner.nextLong()));
    }

    private void removeAddress() {
        System.out.print(inputPersonID + ", von welcher Sie eine Adresse löschen möchten: ");
        long personID = scanner.nextLong();
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

        long id;
        List<Person> persons = personRestService.getAllPersons();

        while (true) {
            System.out.println(inputPersonID + ", der Sie folgen möchten: ");
            try {
                id = scanner.nextLong();

                while (id < 0 || id > persons.size() - 1) {
                    System.out.println("Bitte geben Sie die korrekte ID, der Person der Sie folgen möchten ein: ");
                    id = scanner.nextLong();
                }

                scanner.nextLine();
                break;
            } catch (java.util.InputMismatchException e) {
                scanner.nextLine();
            }
        }

        personRestService.followPerson(id);
    }


    private void unFollowPerson() {

        long id;
        List<Person> persons = personRestService.getAllPersons();

        while (true) {
            System.out.println(inputPersonID + ", der Sie nicht mehr folgen möchten: ");
            try {
                id = scanner.nextLong();

                while (id < 0 || id > persons.size() - 1) {
                    System.out.println("Bitte geben Sie die korrekte ID, der Person der Sie entfolgen möchten ein: ");
                    id = scanner.nextLong();
                }

                scanner.nextLine();
                break;
            } catch (java.util.InputMismatchException e) {
                scanner.nextLine();
            }
        }

        personRestService.unFollowPerson(id);
    }

    private void deletePerson() {

        long id;
        List<Person> persons = personRestService.getAllPersons();

        while (true) {
            System.out.println(inputPersonID + ", die gelöscht werden soll: ");
            try {
                id = scanner.nextLong();

                while (id < 0 || id > persons.size() - 1) {
                    System.out.println("Bitte geben Sie die korrekte ID, der Person die gelöscht werden soll ein: ");
                    id = scanner.nextLong();
                }

                scanner.nextLine();
                break;
            } catch (java.util.InputMismatchException e) {
                scanner.nextLine();
            }
        }
        personRestService.deletePerson(id);
    }

    private void changePerson() {


        long id;
        List<Person> persons = personRestService.getAllPersons();

        while (true) {
            System.out.println(inputPersonID + ", die Sie ändern möchten: ");
            try {
                id = scanner.nextLong();

                while (id < 0 || id > persons.size() - 1) {
                    System.out.println("Bitte geben Sie die korrekte ID, der Person die gelöscht werden soll ein: ");
                    id = scanner.nextLong();
                }

                scanner.nextLine();
                break;
            } catch (java.util.InputMismatchException e) {
                scanner.nextLine();
            }

        }


        if (id == LoggedInPersonSingleton.getLoggedInUserID()) {
            Person person = dataInput();
            person.setId(id);
            personRestService.updatePerson(person);
        } else {
            System.out.println("Sie dürfen keine anderen Benutzer ändern. Sie können nur Ihre eigenen Daten ändern!");
        }

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

        //consume newline
        scanner.nextLine();


        year = loginRestHelperService.getYear();
        month = loginRestHelperService.getMonth();
        day = loginRestHelperService.getDay(year, month);

        System.out.println("\nSie können später eine bestehende Adresse oder Telefonnummer hinzufügen oder ganz neue Einträge erstellen\n");

        return new Person(id, firstName, lastName, day, month, year, null, null);

    }


    private void addPerson() {

        System.out.println("Bitte geben Sie die folgenden Informationen der Person ein, welche Sie hinzufügen wollen:");

        personRestService.addPerson(dataInput());
    }
}
