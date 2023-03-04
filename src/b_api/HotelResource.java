package b_api;

import c_service.CustomerService;
import c_service.ReservationService;
import d_model.Customer;
import d_model.IRoom;
import d_model.Reservation;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

public class HotelResource {
    public static Customer getCustomer(String email){
        return CustomerService.getCustomer(email);
    }
    public static void createACustomer(String firstName, String lastName, String email){
        CustomerService.addCustomer(firstName, lastName, email);
    }

    public static IRoom getRoom(String roomNumber){
        return ReservationService.getARoom(roomNumber);
    }

    public static void bookARoom(String customerEmail, String roomNumber, Date checkInDate, Date checkOutDate){
        ReservationService.reserveARoom(CustomerService.getCustomer(customerEmail),getRoom(roomNumber),checkInDate, checkOutDate);
    }

    public static Collection<Reservation> getCustomersReservations(String customerEmail){
        return ReservationService.getCustomersReservation(CustomerService.getCustomer(customerEmail));
    }

    public static Map<String, IRoom> findARoom(Date checkInDate, Date checkOutDate){
        return ReservationService.findRooms(checkInDate,checkOutDate);
    }
}
