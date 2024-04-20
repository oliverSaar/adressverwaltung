package de.dhbw.ase.service;

import de.dhbw.ase.dao.UserPasswordDAO;
import de.dhbw.ase.model.Person;

import javax.inject.Inject;
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
    public LoginService(final UserPasswordDAO userPasswordDAO, final PersonService personService) {
        this.userPasswordDAO = userPasswordDAO;
        this.personService = personService;
    }


    public HashMap<String, String> getUserPassword() {
        return userPasswordDAO.getUserPassword();
    }

    public void login() {
        userPassword = getUserPassword();
        String username;
        String password;

        username = scanner.nextLine();

        if (userPassword.containsKey(username)) {

            String[] usernameSplit = username.split(" ");
            System.out.println("0 " + usernameSplit[0]);
            System.out.println("1 " + usernameSplit[1]);

            System.out.print("Bitte geben Sie Ihr Passwort ein: ");
            password = scanner.next();
            if (userPassword.get(username).equals(password)) {
                user = personService.getAllPersons()
                        .stream()
                        .filter(person -> person.getFirstName().equals(usernameSplit[0]) && person.getLastName().equals(usernameSplit[1]))
                        .findFirst()
                        .orElseThrow(() ->
                                new IllegalArgumentException("Es konnte keine Person mit dem Benutzernamen: " + usernameSplit[0] + " " + usernameSplit[1] + " gefunden werden"));

                System.out.println("Login erfolgreich");
            } else {
                System.out.println("Login fehlgeschlagen");
            }
        } else {
            System.out.println("Login fehlgeschlagen");
        }

        scanner.close();
    }

    public Person getLoggedInUser() {
        if (user != null) {

            return user;
        } else {
            System.out.println("Keine eingeloggte Person gefunden");
            return null;

        }
    }

    public void register() {

        userPassword = getUserPassword();


        System.out.print("Bitte geben Sie Ihren Benutzernamen ein: ");
        String username = scanner.next();
        if (userPassword.containsKey(username)) {
            System.out.println("Benutzername ist bereits vergeben");
            System.out.println("------------------------------------------");
            register();
        }
        System.out.print("Bitte geben Sie Ihr Passwort ein: ");
        String password = scanner.next();

        userPassword.put(username, password);
        System.out.println("Registrierung erfolgreich");

        scanner.close();
    }
}
