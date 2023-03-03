package a_ui;

import b_api.AdminResource;
import d_model.Customer;

import java.util.Collection;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class AdminMenu {
    public static void generateAdminMenu(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\nHello, welcome to my Hotel Reservation System");
        System.out.println("---------------------------------------------");
        System.out.println("1. See all customers");
        System.out.println("2. See all rooms");
        System.out.println("3. See all reservations");
        System.out.println("4. Add a room");
        System.out.println("5. Add test data");
        System.out.println("6. Back to main menu");
        try{
            int userInput = parseInt(scanner.next());
            if(userInput > 6 || userInput < 1){
                throw new NumberFormatException("\nInvalid input, valid input is 1-6");
            }
            processAdminUserInput(userInput);
        }catch(NumberFormatException ex){
            System.out.println("\nInput is not a number, valid input is 1-6");
            generateAdminMenu();
        }
    }

    public static void processAdminUserInput(int userInput){
        if(userInput == 1){
            Collection<Customer> customers = AdminResource.getAllCustomers();
            System.out.println(customers);
        }
        if(userInput == 2){
            //seeAllRooms();
        }
        if(userInput == 3){
            //seeAllReservations();
        }
        if(userInput == 4){
            //addARoom();
        }
        if(userInput == 5){
            //addTestData();
        }
        if(userInput == 6){
            MainMenu.generateMainMenu();
        }
    }
}
