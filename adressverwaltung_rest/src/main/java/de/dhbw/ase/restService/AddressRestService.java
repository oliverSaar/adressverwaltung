package de.dhbw.ase.restService;

import de.dhbw.ase.model.Address;
import de.dhbw.ase.service.AddressService;

import javax.inject.Inject;

public class AddressRestService {

    @Inject
    private AddressService addressService;
    
    
    public void getAllAddresses(){
        
    }

    public void getAddress(int id) {
    }

    public Address addAddress(Address address) {

        return addressService.addAddress(address);

    }
}
