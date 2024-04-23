package de.dhbw.ase.view;

import de.dhbw.ase.model.Person;
import de.dhbw.ase.restHelperService.AddressRestHelperService;
import de.dhbw.ase.restHelperService.PersonRestHelperService;
import de.dhbw.ase.restHelperService.PhoneNumberRestHelperService;
import de.dhbw.ase.service.LoginService;
import de.dhbw.ase.service.PersonService;

import javax.inject.Inject;
import java.util.Scanner;

public class MainView implements View {

    Scanner scanner = new Scanner(System.in);

    private final PersonRestHelperService personRestHelperService;
    private final AddressRestHelperService addressRestHelperService;
    private final PhoneNumberRestHelperService phoneNumberRestHelperService;
    private final LoginService loginService;

    public MainView(PersonRestHelperService personRestHelperService, AddressRestHelperService addressRestHelperService, PhoneNumberRestHelperService phoneNumberRestHelperService, LoginService loginService) {
        this.personRestHelperService = personRestHelperService;
        this.addressRestHelperService = addressRestHelperService;
        this.phoneNumberRestHelperService = phoneNumberRestHelperService;
        this.loginService = loginService;
    }


    //TODO initiale LoginView hier aufrufen (damit diese instanz)

    public void loginView() {

        int input;

        System.out.println("Haben Sie schon ein Konto?");
        System.out.println("1. Ja");
        System.out.println("2. Nein");
        input = scanner.nextInt();
        if (input == 1) {
            if (loginService.login()) {
                defaultView();
            } else {
                System.out.println("Login fehlgeschlagen");
            }
        } else {
            loginService.register();
            defaultView();
        }

    }


    public void defaultView() {

        int input;

        System.out.println("Wilkommen zur Adressverwaltung");
        System.out.println("Drücken Sie die entsprechenden Zahlen, um zu den Funktionen zu navigieren");
        System.out.println("1. Personen");
        System.out.println("2. Adressen");
        System.out.println("3. Telefonnummern");

        input = scanner.nextInt();

        switch (input) {
            case 1:
                new PersonView(personRestHelperService, addressRestHelperService, phoneNumberRestHelperService, loginService).defaultView();
                break;
            case 2:
                new AddressView(addressRestHelperService, personRestHelperService, phoneNumberRestHelperService, loginService).defaultView();
                break;
            case 3:
                new PhoneNumberView(phoneNumberRestHelperService, personRestHelperService, addressRestHelperService, loginService).defaultView();
                break;
            default:
                break;
        }
        scanner.close();

    }
}
