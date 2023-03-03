package b_api;

import c_service.CustomerService;
import d_model.Customer;
import d_model.IRoom;
import d_model.Reservation;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    public Customer getCustomer(String email){
        return null;
    }
    public static void createACustomer(String firstName, String lastName, String email){
        CustomerService.addCustomer(firstName, lastName, email);
    }

    public IRoom getRoom(String roomNumber){
        return null;
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
        return null;
    }

    public Collection<Reservation> getCustomersReservations(String customerEmail){
        return null;
    }

    public Collection<IRoom> findARoom(Date checkInDate, Date checkOutDate){
        return null;
    }
}
