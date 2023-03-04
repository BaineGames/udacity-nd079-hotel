package b_api;

import c_service.CustomerService;
import c_service.ReservationService;
import d_model.Customer;
import d_model.IRoom;
import d_model.Reservation;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    public Customer getCustomer(String email){
        return CustomerService.getCustomer(email);
    }
    public static void addRoom(String roomNumber, double roomPrice, String roomType){
        ReservationService.addRoom(roomNumber, roomPrice, roomType);
    }

    public static Collection<IRoom> getAllRooms(){
        return ReservationService.getAllRooms();
    }

    public static Collection<Customer> getAllCustomers(){
        return CustomerService.getAllCustomers();
    }

    public static List<Reservation> getAllReservations(){
        return ReservationService.getAllReservations();
    }
}
