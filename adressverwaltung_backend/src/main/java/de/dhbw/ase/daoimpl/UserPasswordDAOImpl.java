package de.dhbw.ase.daoimpl;

import de.dhbw.ase.dao.UserPasswordDAO;

import java.util.HashMap;

public class UserPasswordDAOImpl implements UserPasswordDAO {

    HashMap<String, String> userPassword = new HashMap<>();

    public UserPasswordDAOImpl() {
        userPassword.put("test", "abc");
        userPassword.put("test2", "test2");
        userPassword.put("admin", "admin");
        userPassword.put("Max Mustermann", "123456");
    }
    @Override
    public HashMap<String, String> getUserPassword() {
        return userPassword;
    }

    @Override
    public void insertUserPassword(String userName, String password) {
        userPassword.put(userName, password);

    }
}
