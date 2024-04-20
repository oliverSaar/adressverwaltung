package de.dhbw.ase.view;

import de.dhbw.ase.dao.PersonDAO;
import de.dhbw.ase.dao.UserPasswordDAO;
import de.dhbw.ase.daoimpl.PersonDAOImpl;
import de.dhbw.ase.daoimpl.UserPasswordDAOImpl;
import de.dhbw.ase.model.Person;
import de.dhbw.ase.service.LoginService;
import de.dhbw.ase.service.PersonService;

import java.util.Scanner;


public class LoginView {

    Scanner scanner = new Scanner(System.in);

    UserPasswordDAO userPasswordDAO = new UserPasswordDAOImpl();
    PersonDAO personDAO = new PersonDAOImpl();
    PersonService personService = new PersonService(personDAO);
    LoginService loginService = new LoginService(userPasswordDAO, personService);

    public void defaultView() {

        int input;

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

        System.out.print("Bitte geben Sie ihren Benutzernamen ein: ");
        loginService.login();


    }


    private void register() {

        loginService.register();
        defaultView();

    }

    public Person getLoggedInUser() {
       return loginService.getLoggedInUser();
    }
}


