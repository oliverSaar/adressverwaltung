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

    public List<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    public Address getAddress(long id) {
        return addressService.getAddress(id);
    }

    public void addAddress(Address address) {
        addressService.addAddress(address);
    }
}
