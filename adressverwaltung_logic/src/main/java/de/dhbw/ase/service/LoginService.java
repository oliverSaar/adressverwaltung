package de.dhbw.ase.service;

import de.dhbw.ase.dao.UserPasswordDAO;
import de.dhbw.ase.model.Person;
import de.dhbw.ase.model.singleton.LoggedInPersonSingleton;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class LoginService {

    private UserPasswordDAO userPasswordDAO;

    private PersonService personService;
    private HashMap<String, String> userPassword;
    private Scanner scanner = new Scanner(System.in);


    public LoginService(final UserPasswordDAO userPasswordDAO, PersonService personService) {
        this.userPasswordDAO = userPasswordDAO;
        this.personService = personService;
    }


    public HashMap<String, String> getUserPassword() throws Exception {
        return userPasswordDAO.getUserPassword();
    }

    public boolean login() throws Exception {

        userPassword = getUserPassword();
        Person user;
        String username;
        String password;

        System.out.print("Bitte geben Sie ihren Benutzernamen ein: ");
        username = scanner.nextLine();

        if (userPassword.containsKey(username)) {

            String[] usernameSplit = username.split(" ");

            System.out.print("Bitte geben Sie Ihr Passwort ein: ");
            password = scanner.nextLine();
            if (userPassword.get(username).equals(password)) {
                user = personService.getAllPersons()
                        .stream()
                        .filter(person -> person.getFirstName().equals(usernameSplit[0]) && person.getLastName().equals(usernameSplit[1]))
                        .findFirst()
                        .orElseThrow(() ->
                                new IllegalArgumentException("Es konnte keine Person mit dem Benutzernamen: " + usernameSplit[0] + " " + usernameSplit[1] + " gefunden werden"));


                LoggedInPersonSingleton.setLoggedInUserID(user.getId());

                System.out.println("\nLogin erfolgreich\n");
                return true;
            } else {
                throw new Exception("Falsches Passwort!");
            }
        } else {
            throw new Exception("Nutzer wurde nicht gefunden!");
        }
    }

    public Person getLoggedInUser() throws Exception {
        try {
            return personService.getPerson(LoggedInPersonSingleton.getLoggedInUserID());
        } catch (Exception e) {
            throw new RuntimeException("Es wurde keine eingeloggte Person gefunden!");
        }


    }

    public boolean register() throws Exception {

        userPassword = getUserPassword();

        System.out.print("Bitte geben Sie Ihren Benutzernamen ein (Muster: Vorname Nachname): ");
        String username = scanner.nextLine();
        String[] usernameSplit = username.split(" ");
        if(usernameSplit.length != 2){
            throw new Exception("Benutzername ist ungültig! Sie müssen zuerst Ihren Vornamen dann Ihren Nachnamen eingeben (Max Mustermann).");
        }
        if (userPassword.containsKey(username)) {
            System.out.println("Benutzername ist bereits vergeben. Bitte setzen Sie eine 1 (bzw. 2 und fortlaufend) an Ihren Nachnamen.");
            System.out.println("------------------------------------------");
            register();
        }
        System.out.print("Bitte geben Sie Ihr Passwort ein: ");
        String password = scanner.nextLine();

        System.out.println("Bitte geben Sie Ihr Geburtsdatum ein, um die Registrierung abzuschließen: ");

        int year, month, day;

        year = getYear();
        month = getMonth();
        day = getDay(year, month);

        try {
            personService.addPerson(new Person(1, usernameSplit[0], usernameSplit[1], day, month, year, null, null));
            userPassword.put(username, password);
            userPasswordDAO.insertUserPassword(username, password);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getDay(int year, int month) {

        LocalDate length = LocalDate.of(year, month, 1);
        int day;

        while (true) {
            System.out.print("Tag: ");
            try {
                day = scanner.nextInt();

                while (day < 1 || day > length.lengthOfMonth()) {
                    System.out.println("Falscher Tag! Bitte geben Sie den Tag erneut ein: ");
                    day = scanner.nextInt();
                }

                scanner.nextLine();
                break;
            } catch (java.util.InputMismatchException e) {
                scanner.nextLine();
            }
        }
        return day;
    }

    public int getMonth() {
        int month;
        while (true) {
            System.out.print("Monat (1-12): ");
            try {
                month = scanner.nextInt();

                while (month < 1 || month > 12) {
                    System.out.println("Falscher Monat! Bitte geben Sie den Monat erneut ein: ");
                    month = scanner.nextInt();
                }

                scanner.nextLine();
                break;
            } catch (java.util.InputMismatchException e) {
                scanner.nextLine();
            }
        }

        System.out.println();
        return month;
    }

    public int getYear() {
        int year;
        while (true) {
            System.out.print("Jahr (z.B. 1990): ");
            try {
                year = scanner.nextInt();

                while (year < 1900 || year > LocalDate.now().getYear()) {
                    System.out.println("Falsches Jahr! Bitte geben Sie Ihr Geburtsjahr erneut ein: ");
                    year = scanner.nextInt();
                }

                scanner.nextLine();
                break;
            } catch (java.util.InputMismatchException e) {
                scanner.nextLine();
            }
        }
        System.out.println();
        return year;
    }

    public void getBirthdayView() throws Exception {

        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_CYAN = "\u001B[36m";

        List<Person> following = getLoggedInUser().getFollowing();
        for (Person person : following) {
            if (person.getDateOfBirth().isToday()) {
                System.out.println(ANSI_CYAN + person.getFirstName().toUpperCase() + " " + person.getLastName().toUpperCase() + " HAT HEUTE GEBURTSTAG und wird " +
                        person.getDateOfBirth().getAge() + " Jahre alt!\n" + ANSI_RESET);
            } else {
                throw new Exception("Heute hat niemand der Personen, denen Sie folgen Geburtstag!\n");
            }
        }
    }
}
