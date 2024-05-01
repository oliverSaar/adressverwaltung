package de.dhbw.ase.service;

import de.dhbw.ase.service.mocks.PersonDAOMock;
import de.dhbw.ase.service.mocks.UserPasswordDAOMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {

    private LoginService loginService;
    private PersonService personService;
    private UserPasswordDAOMock userPasswordDAOMock;
    private PersonDAOMock personDAOMock;
    private HashMap<String, String> userPassword;

    @BeforeEach
    void setUp() {

        userPasswordDAOMock = new UserPasswordDAOMock();
        personDAOMock = new PersonDAOMock();
        personService = new PersonService(personDAOMock);
        loginService = new LoginService(userPasswordDAOMock, personService);
        userPassword = new HashMap<>();
        userPassword.put("testV", "testP");
    }

    @Test
    void getUserPassword() throws Exception {
        assertEquals(4, loginService.getUserPassword().size());
    }

    @Test
    void login() throws Exception {
    }

    @Test
    void getLoggedInUserException() {

        Exception exception = assertThrows(Exception.class, () -> loginService.getLoggedInUser());
        String expectedMessage = "Keine eingeloggte Person gefunden!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void register() {
    }

    @Test
    void getBirthdayViewThrowsException() throws Exception {

        Exception exception = assertThrows(Exception.class, () -> loginService.getBirthdayView());
        String expectedMessage = "Keine eingeloggte Person gefunden!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }
}