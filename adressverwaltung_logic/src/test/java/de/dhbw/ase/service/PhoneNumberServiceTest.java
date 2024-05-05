package de.dhbw.ase.service;

import de.dhbw.ase.model.PhoneNumber;
import de.dhbw.ase.service.mocks.PhoneNumberDAOMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PhoneNumberServiceTest {

    private PhoneNumberService phoneNumberService;
    private PhoneNumberDAOMock phoneNumberDAOMock;

    private PhoneNumber toAdd;

    @BeforeEach
    void setUp() {

        phoneNumberDAOMock = new PhoneNumberDAOMock();
        phoneNumberService = new PhoneNumberService(phoneNumberDAOMock);

        toAdd = new PhoneNumber("1234", false);

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