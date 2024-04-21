package de.dhbw.ase.view;

import de.dhbw.ase.restHelperService.PhoneNumberRestHelperService;

public class PhoneNumberView implements View {

    private final PhoneNumberRestHelperService phoneNumberRestHelperService;

    public PhoneNumberView(PhoneNumberRestHelperService phoneNumberRestHelperService) {
        this.phoneNumberRestHelperService = phoneNumberRestHelperService;
    }

    public void defaultView() {
    }

    public void addPhoneNumber() {

    }
}
