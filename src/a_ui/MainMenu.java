package a_ui;
import b_api.HotelResource;
import d_model.Customer;
import d_model.IRoom;
import d_model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    public static void processUserInput(int userInput) {
        switch(userInput){
            case 1:
                findAndReserveARoom();
                break;
            case 2:
                seeMyReservations();
                break;
            case 3:
                createMyAccount();
                break;
            case 4:
                AdminMenu.generateAdminMenu();
                break;
            case 5:
                System.out.println("Thank you for staying with us, come again soon");
                break;
        }
    }

    public static void createMyAccount(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("First name?");
        String firstName = scanner.nextLine();
        System.out.println("Last name?");
        String lastName = scanner.nextLine();
        System.out.println("Email?");
        String email = scanner.nextLine();

        HotelResource.createACustomer(firstName, lastName, email);
        generateMainMenu();
    }

    public static void findAndReserveARoom() {
        SimpleDateFormat checkDate = new SimpleDateFormat("mm-dd-yyyy");
        Date checkin = null;
        Date checkout = null;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Do you have an existing account with us? y/n");
        String hasAccount = scanner.nextLine();

        String userEmail = null;
        switch (hasAccount) {
            case "y":
                System.out.println("What is the email address the account is under?");
                userEmail = scanner.nextLine();

                Customer loggedInUser = HotelResource.getCustomer(userEmail);
                if (loggedInUser == null) {
                    System.out.println("Account not found, try again.");
                    findAndReserveARoom();
                }
                System.out.println("Logged in as " + loggedInUser);
                break;
            case "n":
                System.out.println("You must create an account first.");
                generateMainMenu();
                break;
            default:
                findAndReserveARoom();
                break;
        }

        try {
            System.out.println("What is your checkin date (mm-dd-yyyy)?");
            checkin = checkDate.parse(scanner.nextLine());
            System.out.println("What is your checkout date (mm-dd-yyyy)?");
            checkout = checkDate.parse(scanner.nextLine());
            if (checkout.before(checkin)) {
                findAndReserveARoom();
            }
        } catch (ParseException e) {
            System.out.println("Invalid input, lets try again");
            findAndReserveARoom();
        }
        //have the customer and dates, need to find the rooms
        Map<String, IRoom> availableRooms = HotelResource.findARoom(checkin, checkout);
        if (availableRooms.isEmpty()) {
            System.out.println("No available rooms currently.");
        } else {
            System.out.println("Available rooms found as follows:");
        }
        availableRooms.forEach((s, room) -> {
            System.out.println(room.getRoomNumber() + " - " + room.getRoomType() + " - " + room.getRoomPrice());
        });

        System.out.println("Please input what room number you would like to reserve:");

        String reserveRoomNumber = scanner.nextLine();

        //todo - validate input
        HotelResource.bookARoom(userEmail,reserveRoomNumber,checkin, checkout);

        System.out.println("Reservation confirmed!");

        generateMainMenu();
    }

    public static void seeMyReservations(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("What email do you need to see reservations for?");
        String userEmail = scanner.nextLine();
        Collection<Reservation> myReservations = HotelResource.getCustomersReservations(userEmail);
        Iterator<Reservation> iterator = myReservations.iterator();
        while (iterator.hasNext()) {
            Reservation activeResy = iterator.next();
            System.out.println(activeResy);
        }
    }
}