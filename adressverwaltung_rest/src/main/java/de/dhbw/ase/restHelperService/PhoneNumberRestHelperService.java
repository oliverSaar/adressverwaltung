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
        return phoneNumberService.getPhoneNumber(id);
    }

    public List<PhoneNumber> getAllPhoneNumbers() {
        return phoneNumberService.getAllPhoneNumbers();
    }

    public void addPhoneNumber(PhoneNumber phoneNumber) {
        phoneNumberService.addPhoneNumber(phoneNumber);
    }
}
