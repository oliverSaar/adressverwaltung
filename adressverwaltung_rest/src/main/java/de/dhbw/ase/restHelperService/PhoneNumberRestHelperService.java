package de.dhbw.ase.restHelperService;

import de.dhbw.ase.model.PhoneNumber;
import de.dhbw.ase.service.PhoneNumberService;

import javax.inject.Inject;
import java.util.List;

public class PhoneNumberRestHelperService {

    @Inject
    private PhoneNumberService phoneNumberService;

    @Inject
    public PhoneNumberRestHelperService(PhoneNumberService phoneNumberService) {
        this.phoneNumberService = phoneNumberService;
    }

    public PhoneNumber getPhoneNumber(long id) {
        try {
            return phoneNumberService.getPhoneNumber(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<PhoneNumber> getAllPhoneNumbers() {
        try {
            return phoneNumberService.getAllPhoneNumbers();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void addPhoneNumber(PhoneNumber phoneNumber) {
        try {
            phoneNumberService.addPhoneNumber(phoneNumber);
        } catch (Exception e) {
            System.out.println("Die Telefonnummer konnte nicht hinzugefügt werden!");
        }
    }
}
