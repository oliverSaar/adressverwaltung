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
        return loginService.login();
    }

    public void register() {
        loginService.register();
    }

    public Person getLoggedInUser() {
        return loginService.getLoggedInUser();
    }

    public void getBirthdayView() {
        loginService.getBirthdayView();
    }
}
