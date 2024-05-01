package de.dhbw.ase.service.mocks;

import de.dhbw.ase.daoimpl.UserPasswordDAOImpl;

import java.util.HashMap;

public class UserPasswordDAOMock extends UserPasswordDAOImpl {

    HashMap<String, String> userPassword = new HashMap<>();

    public UserPasswordDAOMock() {
        userPassword.put("test user", "abc");
        userPassword.put("test2 user", "test2");
        userPassword.put("admin user", "admin");
        userPassword.put("Max Mustermann", "123456");
    }

    @Override
    public HashMap<String, String> getUserPassword() throws Exception {
        return userPassword;
    }

    @Override
    public void insertUserPassword(String userName, String password) throws Exception {
        userPassword.put(userName, password);

    }

}
