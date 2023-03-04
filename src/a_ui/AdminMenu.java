package a_ui;

import b_api.AdminResource;
import c_service.ReservationService;
import d_model.*;

import java.util.Collection;
import java.util.IllegalFormatException;
import java.util.List;
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
        System.out.println("5. Back to main menu");
        try{
            int userInput = parseInt(scanner.nextLine());
            if(userInput > 5 || userInput < 1){
                throw new NumberFormatException("\nInvalid input, valid input is 1-5");
            }
            processAdminUserInput(userInput);
        }catch(NumberFormatException ex){
            System.out.println("\nInput is not a number");
            generateAdminMenu();
        }
    }

    public static void processAdminUserInput(int userInput){
        switch(userInput){
            case 1:
                Collection<Customer> customers = AdminResource.getAllCustomers();
                for (Customer customer : customers) {
                    System.out.println(customer.toString());
                }
                generateAdminMenu();
                break;
            case 2:
                Collection<IRoom> allRooms = AdminResource.getAllRooms();
                for (IRoom room : allRooms) {
                    System.out.println(room.toString());
                }
                generateAdminMenu();
                break;
            case 3:
                AdminResource.getAllReservations();
                generateAdminMenu();
                break;
            case 4:
                addNewRoom();
                break;
            case 5:
                MainMenu.generateMainMenu();
                break;
        }
    }

    private static void addNewRoom() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter a room number:");

        String roomNumber = null;
        try{
            roomNumber = scanner.nextLine();
            parseInt(roomNumber);
        }catch(IllegalFormatException ex){
            System.out.println("Room number must be a number");
        }

        System.out.println("Please enter room type:");
        System.out.println("1 ---- Single Bed Room");
        System.out.println("2 ---- Double Bed Room");
        System.out.println("3 ---- Master Suite");

        String roomType = null;
        try {
            roomType = scanner.nextLine();
            int numChecker = parseInt(roomType);
            if (numChecker > 3 || numChecker < 1) {
                throw new NumberFormatException("\nInvalid input, valid input is 1-3");
            }
        } catch (NumberFormatException ex) {
            System.out.println("\nInput is not a number, valid input is 1-3");
            generateAdminMenu();
        }

        System.out.println("Please enter the nightly price in USD:");

        Double roomPrice = null;
        try {
            roomPrice = scanner.nextDouble();

            if (roomPrice.isNaN()) {
                throw new NumberFormatException("\nInvalid input detected, valid input is ##.##");
            }
        } catch (NumberFormatException ex) {
            System.out.println("\nInvalid input detected, valid input is ##.##");
            generateAdminMenu();
        }

        AdminResource.addRoom(roomNumber, roomPrice, roomType);
        generateAdminMenu();

    }
}
