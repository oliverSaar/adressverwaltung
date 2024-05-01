package de.dhbw.ase.service;

import de.dhbw.ase.model.PhoneNumber;
import de.dhbw.ase.service.mocks.PhoneNumberDAOMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberServiceTest {

    private PhoneNumberService phoneNumberService;
    private PhoneNumberDAOMock phoneNumberDAOMock;

    private PhoneNumber toAdd;

    @BeforeEach
    void setUp() {

        phoneNumberDAOMock = new PhoneNumberDAOMock();
        phoneNumberService = new PhoneNumberService(phoneNumberDAOMock);

        toAdd = new PhoneNumber(0, "1234", false);

    }

    @Test
    void getPhoneNumber() throws Exception {

        assertEquals("111111111", phoneNumberService.getPhoneNumber(0).getNumber());
        assertFalse(phoneNumberService.getPhoneNumber(0).isMobile());
    }

    @Test
    void getPhoneNumberException() throws Exception {

        Exception exception = assertThrows(Exception.class, () -> phoneNumberService.getPhoneNumber(4));

        String expectedMessage = "Die Telefonnummer mit der ID: 4 konnte nicht gefunden werden!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void getAllPhoneNumbers() throws Exception {

        assertEquals(3, phoneNumberService.getAllPhoneNumbers().size());
        assertEquals("111111111", phoneNumberService.getAllPhoneNumbers().get(0).getNumber());

    }

    @Test
    void addPhoneNumber() throws Exception {

        assertEquals(3, phoneNumberService.getAllPhoneNumbers().size());
        phoneNumberService.addPhoneNumber(toAdd);
        assertEquals(4, phoneNumberService.getAllPhoneNumbers().size());
    }
}