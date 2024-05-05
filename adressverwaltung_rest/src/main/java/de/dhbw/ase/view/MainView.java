package de.dhbw.ase.view;

import de.dhbw.ase.restHelperService.AddressRestHelperService;
import de.dhbw.ase.restHelperService.LoginRestHelperService;
import de.dhbw.ase.restHelperService.PersonRestHelperService;
import de.dhbw.ase.restHelperService.PhoneNumberRestHelperService;

import java.util.Scanner;

public class MainView implements View {

    Scanner scanner = new Scanner(System.in);

    private final PersonRestHelperService personRestHelperService;
    private final AddressRestHelperService addressRestHelperService;
    private final PhoneNumberRestHelperService phoneNumberRestHelperService;
    private final LoginRestHelperService loginRestHelperService;

    public MainView(PersonRestHelperService personRestHelperService, AddressRestHelperService addressRestHelperService, PhoneNumberRestHelperService phoneNumberRestHelperService, LoginRestHelperService loginRestHelperService) {
        this.personRestHelperService = personRestHelperService;
        this.addressRestHelperService = addressRestHelperService;
        this.phoneNumberRestHelperService = phoneNumberRestHelperService;
        this.loginRestHelperService = loginRestHelperService;
    }


    public void loginView() {

        int input;

        System.out.println("\n-----------------------------------------------\n");
        System.out.println("Haben Sie schon ein Konto? Bitte geben Sie die Nummer ein und bestätigen mit Enter");
        System.out.println("1. Ja");
        System.out.println("2. Nein");
        input = scanner.nextInt();

        //consume last line
        scanner.nextLine();

        if (input == 1) {
            loginRestHelperService.login();
            loginView();
        } else {
            loginRestHelperService.register();
            loginView();
        }


    }


    public void defaultView() {

        loginRestHelperService.getBirthdayView();

        int input;

        consoleMenu();

        input = scanner.nextInt();

        switch (input) {
            case 1:
                new PersonView(personRestHelperService, addressRestHelperService, phoneNumberRestHelperService, loginRestHelperService).defaultView();
                break;
            case 2:
                new AddressView(addressRestHelperService, personRestHelperService, phoneNumberRestHelperService, loginRestHelperService).defaultView();
                break;
            case 3:
                new PhoneNumberView(phoneNumberRestHelperService, personRestHelperService, addressRestHelperService, loginRestHelperService).defaultView();
                break;
            case 4:
                break;
            default:
                System.out.println("Falsche Eingabe");
                defaultView();
                break;
        }
        scanner.close();

    }

    @Override
    public void consoleMenu() {
        System.out.println("Wilkommen zur Adressverwaltung");
        System.out.println("Drücken Sie die entsprechenden Nummern und bestätigen mit Enter, um zu den Funktionen zu navigieren");
        System.out.println("1. Personen");
        System.out.println("2. Adressen");
        System.out.println("3. Telefonnummern");
        System.out.println("4. Beenden");
    }
}
