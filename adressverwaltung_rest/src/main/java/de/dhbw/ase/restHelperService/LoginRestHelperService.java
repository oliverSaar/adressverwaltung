package de.dhbw.ase.restHelperService;

import de.dhbw.ase.model.Person;
import de.dhbw.ase.service.LoginService;

import javax.inject.Inject;

public class LoginRestHelperService {

    @Inject
    LoginService loginService;

    @Inject
    public LoginRestHelperService(LoginService loginService) {
        this.loginService = loginService;
    }


    public boolean login() {
        try {
            return loginService.login();
        } catch (Exception e) {
            System.out.println("Der Login konnte nicht erfolgreich durchgeführt werden! Bitte versuchen Sie es erneut. " + e.getMessage());
            return false;
        }
    }

    public boolean register() {
        try {
            loginService.register();
            System.out.println("Die Registrierung war erfolgreich! Sie können sich nun einloggen.");
        } catch (Exception e) {
            System.out.println("Die Registrierung konnte nicht erfolgreich durchgeführt werden! " + e.getMessage());
            return false;
        }
        return false;
    }

    public Person getLoggedInUser() {
        try {
            return loginService.getLoggedInUser();
        } catch (Exception e) {
            System.out.println("Keine eingeloggte Person gefunden!");
            return null;
        }
    }

    public void getBirthdayView() {
        try {
            loginService.getBirthdayView();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getYear() {
        return loginService.getYear();
    }

    public int getMonth() {
        return loginService.getMonth();
    }

    public int getDay(int year, int month) {
        return loginService.getDay(year, month);
    }
}
