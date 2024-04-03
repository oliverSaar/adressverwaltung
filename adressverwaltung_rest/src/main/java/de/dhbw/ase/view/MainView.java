package de.dhbw.ase.view;

import java.util.Scanner;

public class MainView {

    Scanner scanner = new Scanner(System.in);

    PersonView personView = new PersonView();
    AddressView addressView = new AddressView();

    PhoneNumberView phoneNumberView = new PhoneNumberView();
    public void defaultView(){

        int input;

        System.out.println("Wilkommen zur Adressverwaltung");
        System.out.println("Drücken Sie die entsprechenden Zahlen, um zu den Funktionen zu navigieren");
        System.out.println("1. Personen");
        System.out.println("2. Adressen");
        System.out.println("3. Telefonnummern");

        input = scanner.nextInt();

        switch (input) {
            case 1:
                personView.defaultView();
                break;
            case 2:
                addressView.defaultView();
                break;
            case 3:
                phoneNumberView.defaultView();
                break;
            default:
                break;
        }


    }
}
