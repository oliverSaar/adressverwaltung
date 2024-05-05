package de.dhbw.ase.view;

import de.dhbw.ase.model.PhoneNumber;
import de.dhbw.ase.restHelperService.AddressRestHelperService;
import de.dhbw.ase.restHelperService.LoginRestHelperService;
import de.dhbw.ase.restHelperService.PersonRestHelperService;
import de.dhbw.ase.restHelperService.PhoneNumberRestHelperService;

import java.util.List;
import java.util.Scanner;

public class PhoneNumberView implements View {

    private final PhoneNumberRestHelperService phoneNumberRestHelperService;
    private final PersonRestHelperService personRestHelperService;
    private final AddressRestHelperService addressRestHelperService;
    private final LoginRestHelperService loginService;
    private final Scanner scanner = new Scanner(System.in);

    public PhoneNumberView(PhoneNumberRestHelperService phoneNumberRestHelperService, PersonRestHelperService personRestHelperService, AddressRestHelperService addressRestHelperService, LoginRestHelperService loginService) {
        this.phoneNumberRestHelperService = phoneNumberRestHelperService;
        this.personRestHelperService = personRestHelperService;
        this.addressRestHelperService = addressRestHelperService;
        this.loginService = loginService;
    }

    public void defaultView() {

        boolean done = false;
        while (!done) {

            consoleMenu();

            int input = scanner.nextInt();

            //consume last line
            scanner.nextLine();
            switch (input) {
                case 1:
                    getAllPhoneNumbers();
                    break;
                case 2:
                    getPerson();
                    break;
                case 3:
                    addPhoneNumber();
                    break;
                case 4:
                    done = true;
                    break;
                default:
                    done = true;
                    break;

            }
        }
        new MainView(personRestHelperService, addressRestHelperService, phoneNumberRestHelperService, loginService).defaultView();
    }

    private void getPerson() {

        System.out.print("Bitte geben Sie die ID der Telefonnummer ein: ");
        format(List.of(phoneNumberRestHelperService.getPhoneNumber(scanner.nextLong())));

    }

    private void getAllPhoneNumbers() {
        List<PhoneNumber> phoneNumbers = phoneNumberRestHelperService.getAllPhoneNumbers();
        format(phoneNumbers);

    }

    private static void format(List<PhoneNumber> phoneNumbers) {
        String leftAlignFormat = "| %-5d | %-25s | %-5s |%n";
        System.out.format("+-------+---------------------------+-------+%n");

        System.out.format("|   ID  |   Nummer                  | Mobil |%n");
        System.out.format("+-------+---------------------------+-------+%n");

        for (PhoneNumber p : phoneNumbers) {

            System.out.format(leftAlignFormat, p.getId(), p.getNumber(), p.isMobile() ? "Ja" : "Nein");
        }
        System.out.format("+-------+---------------------------+-------+%n");
    }

    public void addPhoneNumber() {

        long id = 0;
        String number;
        boolean mobile;

        System.out.println("Bitte geben Sie die Nummer ein: ");
        number = scanner.nextLine();
        System.out.println("Ist die Nummer mobil? (Ja/Nein)");
        mobile = scanner.nextLine().equalsIgnoreCase("Ja");

        PhoneNumber phoneNumber = new PhoneNumber(id, number, mobile);
        phoneNumberRestHelperService.addPhoneNumber(phoneNumber);
    }


    public void consoleMenu() {
        System.out.println("Bitte geben Sie die Zahl der Aktion ein: ");

        System.out.println("1. Alle Telefonnummern anzeigen");
        System.out.println("2. Eine Telefonnummer anzeigen (ID benötigt)");
        System.out.println("3. Eine Telefonnummer hinzufügen");
        System.out.println("4. Zurück zum Hauptmenü");
    }
}
