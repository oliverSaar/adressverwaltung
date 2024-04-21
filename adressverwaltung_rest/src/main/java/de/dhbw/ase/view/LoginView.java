package de.dhbw.ase.view;

import de.dhbw.ase.model.Person;
import de.dhbw.ase.restHelperService.PersonRestHelperService;
import de.dhbw.ase.service.LoginService;

import java.util.Scanner;


public class LoginView implements View {

    Scanner scanner = new Scanner(System.in);

    private final LoginService loginService;
    private final PersonRestHelperService personRestHelperService;

    public LoginView(LoginService loginService, PersonRestHelperService personRestHelperService) {
        this.loginService = loginService;
        this.personRestHelperService = personRestHelperService;
    }

    public void defaultView() {


    }


    public boolean login() {

        System.out.print("Bitte geben Sie ihren Benutzernamen ein: ");
        if (loginService.login()) {

            return true;
            //TODO anders lösen: in MainView die Funktion login aufrufen und dann breaken;
//            MainView mainView = new MainView();
//            mainView.defaultView();
        } else {
            return false;
        }
    }


    public void register() {

        loginService.register();

    }

    public Person getLoggedInUser() {
        return loginService.getLoggedInUser();
    }
}


