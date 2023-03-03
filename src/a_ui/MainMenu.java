package a_ui;
import b_api.HotelResource;

import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class MainMenu {

    public static void generateMainMenu(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\nHello, welcome to my Hotel Reservation System");
        System.out.println("---------------------------------------------");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin options");
        System.out.println("5. Exit");
        try{
            int userInput = parseInt(scanner.next());
            if(userInput > 5 || userInput < 1){
                throw new NumberFormatException("\nInvalid input, valid input is 1-5");
            }
            processUserInput(userInput);
        }catch(NumberFormatException ex){
            System.out.println("\nInput is not a number, valid input is 1-5");
            generateMainMenu();
        }
    }

    public static void processUserInput(int userInput){
        if(userInput == 1){
            //findAndReserveARoom();
        }
        if(userInput == 2){
            //seeMyReservations();
        }
        if(userInput == 3){
            createMyAccount();
        }
        if(userInput == 4){
            AdminMenu.generateAdminMenu();
        }
        if(userInput == 5){
            System.out.println("Thank you for staying with us.");
        }
    }

    public static void createMyAccount(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("First name?");
        String firstName = scanner.next();
        System.out.println("Last name?");
        String lastName = scanner.next();
        System.out.println("Email?");
        String email = scanner.next();

        HotelResource.createACustomer(firstName, lastName, email);

        generateMainMenu();


    }
}