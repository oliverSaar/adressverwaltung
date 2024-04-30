package de.dhbw.ase.dao;

import java.util.HashMap;

public interface UserPasswordDAO {


    HashMap<String, String> getUserPassword() throws Exception;

    void insertUserPassword(String userName, String password) throws Exception;

}
