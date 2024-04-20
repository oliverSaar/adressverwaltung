package de.dhbw.ase.service;

import de.dhbw.ase.dao.UserPasswordDAO;

import javax.inject.Inject;
import java.util.HashMap;

public class LoginService {

    private UserPasswordDAO userPasswordDAO;

    protected LoginService() {
    }

    @Inject
    public LoginService(final UserPasswordDAO userPasswordDAO) {
        this.userPasswordDAO = userPasswordDAO;
    }


    public HashMap<String, String> getUserPassword() {
        return userPasswordDAO.getUserPassword();
    }

}
