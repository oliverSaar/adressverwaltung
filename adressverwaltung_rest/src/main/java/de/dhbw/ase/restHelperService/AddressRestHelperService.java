package de.dhbw.ase.restHelperService;

import de.dhbw.ase.model.Address;
import de.dhbw.ase.service.AddressService;


import javax.inject.Inject;

public class AddressRestHelperService {


    @Inject
    private AddressService addressService;

    @Inject
    public AddressRestHelperService(AddressService addressService) {
        this.addressService = addressService;
    }

    public void getAllAddresses() {
    }

    public void getAddress(int i) {
    }

    public void addAddress(Address address) {
    }
}
