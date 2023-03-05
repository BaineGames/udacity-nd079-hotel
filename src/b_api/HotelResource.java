package b_api;

import c_service.CustomerService;
import c_service.ReservationService;
import d_model.Customer;
import d_model.IRoom;
import d_model.Reservation;

import java.util.Collection;
import java.util.Date;

public class HotelResource {

    private static final CustomerService customer = CustomerService.getInstance();
    private static final ReservationService reservation = ReservationService.getInstance();
    public static Customer getCustomer(String email){
        return customer.getCustomer(email);
    }
    public static void createACustomer(String firstName, String lastName, String email){
        customer.addCustomer(firstName, lastName, email);
    }

    public static IRoom getRoom(String roomNumber){
        return reservation.getARoom(roomNumber);
    }

    public static void bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
        reservation.reserveARoom(customer.getCustomer(customerEmail),room,checkInDate, checkOutDate);
    }

    public static Collection<Reservation> getCustomersReservations(String customerEmail){

        return reservation.getCustomersReservation(customer.getCustomer(customerEmail));
    }

    public static Collection<IRoom> findARoom(Date checkInDate, Date checkOutDate){
        return reservation.findRooms(checkInDate,checkOutDate);
    }
}
