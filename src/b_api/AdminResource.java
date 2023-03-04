package b_api;

import c_service.CustomerService;
import c_service.ReservationService;
import d_model.Customer;
import d_model.IRoom;
import d_model.Reservation;

import java.util.Collection;

public class AdminResource {

    private static final CustomerService customer = CustomerService.getInstance();
    private static final ReservationService reservation = ReservationService.getInstance();
    public static Customer getCustomer(String email){
        return customer.getCustomer(email);
    }
    public static void addRoom(String roomNumber, double roomPrice, String roomType){
        reservation.addRoom(roomNumber, roomPrice, roomType);
    }

    public static Collection<IRoom> getAllRooms(){
        return reservation.getAllRooms();
    }

    public static Collection<Customer> getAllCustomers(){
        return customer.getAllCustomers();
    }

    public static void getAllReservations(){
        reservation.getAllReservations();
    }
}
