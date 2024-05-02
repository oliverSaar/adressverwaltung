package de.dhbw.ase.restHelperService;

import de.dhbw.ase.model.Address;
import de.dhbw.ase.service.AddressService;

import javax.inject.Inject;
import java.util.List;

public class AddressRestHelperService {


    @Inject
    private AddressService addressService;

    @Inject
    public AddressRestHelperService(AddressService addressService) {
        this.addressService = addressService;
    }

    public Address getAddress(long id) {
        try {
            return addressService.getAddress(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Address> getAllAddresses() {
        try {
            return addressService.getAllAddresses();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void addAddress(Address address) {
        try {
            addressService.addAddress(address);
            System.out.println("Die Adresse wurde hinzugefügt!");
        } catch (Exception e) {
            System.out.println("Die Adresse konnte nicht hinzugefügt werden! Bitte versuchen Sie es erneut!");
        }
    }
}
