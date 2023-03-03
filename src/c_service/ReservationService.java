package c_service;

import d_model.Customer;
import d_model.IRoom;
import d_model.Reservation;
import d_model.Room;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ReservationService {

    private static final Map<String, IRoom> roomData = new HashMap<>();
    private static final Map<String, Reservation> reservationData = new HashMap<>();
    public void addRoom(IRoom room){
        roomData.put(room.getRoomNumber(),room);
    }
    public IRoom getARoom(String roomId){
        return null;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        //Reservation myRes = new Reservation();
        return null;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
        return null;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer){
        return null;
    }

    public void printAllReservation(){

    }
}
