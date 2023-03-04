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
        return CustomerService.getInstance().getCustomer(email);
    }
    public static void createACustomer(String firstName, String lastName, String email){
        CustomerService.getInstance().addCustomer(firstName, lastName, email);
    }

    public static IRoom getRoom(String roomNumber){
        return ReservationService.getInstance().getARoom(roomNumber);
    }

    public static void bookARoom(String customerEmail, String roomNumber, Date checkInDate, Date checkOutDate){
        ReservationService.getInstance().reserveARoom(CustomerService.getInstance().getCustomer(customerEmail),getRoom(roomNumber),checkInDate, checkOutDate);
    }

    public static Collection<Reservation> getCustomersReservations(String customerEmail){
        return ReservationService.getInstance().getCustomersReservation(CustomerService.getInstance().getCustomer(customerEmail));
    }

    public static Map<String, IRoom> findARoom(Date checkInDate, Date checkOutDate){
        return ReservationService.getInstance().findRooms(checkInDate,checkOutDate);
    }
}
