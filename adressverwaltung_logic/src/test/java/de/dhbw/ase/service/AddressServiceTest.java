package de.dhbw.ase.service;

import de.dhbw.ase.model.Address;
import de.dhbw.ase.service.mocks.AddressDAOMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressServiceTest {

    public AddressDAOMock addressDAOMock;
    public AddressService addressService;

    public Address toAdd;

    @BeforeEach
    void setUp() {

        addressDAOMock = new AddressDAOMock();
        addressService = new AddressService(addressDAOMock);

        toAdd = new Address(0, "country", 12345, "city", "streetName", "7357A");

    }

    @Test
    void addAddress() throws Exception {
        assertEquals(3, addressService.getAllAddresses().size());
        addressService.addAddress(toAdd);
        assertEquals(4, addressService.getAllAddresses().size());
    }

    @Test
    void getAddress() throws Exception {
        assertEquals(0, addressService.getAddress(0).getId());
        assertEquals("country", addressService.getAddress(0).getCountry());
        assertEquals(11111, addressService.getAddress(0).getZipCode());
        assertEquals("city", addressService.getAddress(0).getCity());
        assertEquals("street", addressService.getAddress(0).getStreetName());
        assertEquals("housenumber", addressService.getAddress(0).getHouseNumber());
    }

    @Test
    void getAddressThrowsException() {
        Exception exception = assertThrows(Exception.class, () -> addressService.getAddress(6));

        String expectedMessage = "Adresse mit der ID: 6 konnte nicht gefunden werden";
        String actualMessage = exception.getMessage();
        System.out.println(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void getAllAddresses() throws Exception {

        assertEquals(3, addressService.getAllAddresses().size());
        addressService.addAddress(toAdd);
        assertEquals(4, addressService.getAllAddresses().size());
    }
}