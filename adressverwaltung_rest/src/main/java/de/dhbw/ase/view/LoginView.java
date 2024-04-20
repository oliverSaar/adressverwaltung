package de.dhbw.ase.view;

import de.dhbw.ase.dao.UserPasswordDAO;
import de.dhbw.ase.daoimpl.UserPasswordDAOImpl;
import de.dhbw.ase.service.LoginService;

import java.util.HashMap;
import java.util.Scanner;


public class LoginView {

    Scanner scanner = new Scanner(System.in);

    UserPasswordDAO userPasswordDAO = new UserPasswordDAOImpl();
    LoginService loginService = new LoginService(userPasswordDAO);
    HashMap<String, String> userPassword = loginService.getUserPassword();

    public void defaultView() {

        int input;


//TODO implement login
        System.out.println("Haben Sie schon ein Konto?");
        System.out.println("1. Ja");
        System.out.println("2. Nein");
        input = scanner.nextInt();
        if (input == 1) {
            login();
        } else {
            register();
        }
    }


    private void login() {
        String username;
        String password;

        System.out.print("Bitte geben Sie ihren Benutzernamen ein: ");
        username = scanner.next();
        if(userPassword.containsKey(username)) {
            System.out.print("Bitte geben Sie Ihr Passwort ein: ");
            password = scanner.next();
            if(userPassword.get(username).equals(password)) {
                System.out.println("Login erfolgreich");
            } else {
                System.out.println("Login fehlgeschlagen");
            }
        } else {
            System.out.println("Login fehlgeschlagen");
        }

    }


    private void register() {

        System.out.print("Bitte geben Sie Ihren Benutzernamen ein: ");
        String username = scanner.next();
        if(userPassword.containsKey(username)) {
            System.out.println("Benutzername ist bereits vergeben");
            System.out.println("------------------------------------------");
            register();
        }
        System.out.print("Bitte geben Sie Ihr Passwort ein: ");
        String password = scanner.next();

        userPassword.put(username, password);
        System.out.println("Registrierung erfolgreich");

    }
}


