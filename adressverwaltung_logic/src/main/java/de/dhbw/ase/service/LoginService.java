package de.dhbw.ase.service;

import de.dhbw.ase.dao.UserPasswordDAO;
import de.dhbw.ase.model.Person;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

public class LoginService {

    private UserPasswordDAO userPasswordDAO;

    private PersonService personService;
    private Person user;
    HashMap<String, String> userPassword;
    Scanner scanner = new Scanner(System.in);


    protected LoginService() {
    }

    @Inject
    public LoginService(final UserPasswordDAO userPasswordDAO, PersonService personService) {
        this.userPasswordDAO = userPasswordDAO;
        this.personService = personService;
    }


    public HashMap<String, String> getUserPassword() {
        return userPasswordDAO.getUserPassword();
    }

    public boolean login() {

        resetLoginFlags();

        userPassword = getUserPassword();
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

                user.setLoggedIn(true);
                personService.updatePerson(user);
                System.out.println("Login erfolgreich");
                return true;
            } else {
                System.out.println("Login fehlgeschlagen 1");
                return false;
            }
        } else {
            System.out.println("Login fehlgeschlagen 2");
            return false;
        }

    }

    private void resetLoginFlags() {
        for (Person person : personService.getAllPersons()) {
            person.setLoggedIn(false);
        }
    }

    public Person getLoggedInUser() {

        for (Person person : personService.getAllPersons()) {
            if (person.isLoggedIn()) {
                return person;
            }
        }
        System.out.println("Keine eingeloggte Person gefunden");
        return null;


    }

    public void register() {

        userPassword = getUserPassword();

        System.out.print("Bitte geben Sie Ihren Benutzernamen ein: ");
        String username = scanner.nextLine();
        String[] usernameSplit = username.split(" ");
        if (userPassword.containsKey(username)) {
            System.out.println("Benutzername ist bereits vergeben");
            System.out.println("------------------------------------------");
            register();
        }
        System.out.print("Bitte geben Sie Ihr Passwort ein: ");
        String password = scanner.nextLine();

        System.out.println("Bitte geben Sie Ihr Geburtsdatum ein, um die Registrierung abzuschließen: ");


        System.out.print("Tag:");
        int day = scanner.nextInt();
        System.out.println();
        System.out.print("Monat (1-12): ");
        int month = scanner.nextInt();
        System.out.println();
        System.out.print("Jahr (z.B. 1990): ");

        int year = scanner.nextInt();

        while (year < 1900 || year > LocalDate.now().getYear()) {
            System.out.println("Falsches Jahr! Bitte geben Sie ihr Geburtsjahr erneut ein: ");
            year = scanner.nextInt();
            //consume unneccesary nextLine
            scanner.nextLine();
        }


        personService.addPerson(new Person(1, usernameSplit[0], usernameSplit[1], day, month, year, null, null));
        userPassword.put(username, password);

        //consume unneccesary nextLine
        scanner.nextLine();

        System.out.println("Registrierung erfolgreich");

        login();

    }
}
