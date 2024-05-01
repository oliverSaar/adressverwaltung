package de.dhbw.ase.service;

import de.dhbw.ase.dao.AddressDAO;
import de.dhbw.ase.model.Address;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class AddressService {

    private AddressDAO addressDAO;


    public AddressService(final AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    private final static AtomicLong ID_COUNTER = new AtomicLong();

    public static long getNextId() {
        return ID_COUNTER.incrementAndGet();
    }

    public void addAddress(Address address) throws Exception {
        address.setId(getNextId());
        addressDAO.insertAddress(address);
    }

    public Address getAddress(long id) throws Exception {

        Optional<Address> optionalAddress = addressDAO.getAddress(id);

        return optionalAddress
                .orElseThrow(() -> new IllegalArgumentException("Adresse mit der ID: " + id + " konnte nicht gefunden werden"));

    }

    public List<Address> getAllAddresses() throws Exception {
        return addressDAO.getAddresses();
    }
}
