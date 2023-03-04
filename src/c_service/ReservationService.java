package c_service;

import d_model.*;

import javax.naming.spi.ResolveResult;
import java.util.*;

import static d_model.RoomType.*;

public class ReservationService {

    private static final Map<String, IRoom> roomData = new HashMap<>();
    private static final List<Reservation> reservationData = new ArrayList<>();
    public static void addRoom(String roomNumber, double roomPrice, String roomType){
        RoomType selectedRoomType = null;
        switch (roomType) {
            case "1":
                selectedRoomType = SINGLE;
                break;
            case "2":
                selectedRoomType = DOUBLE;
                break;
            case "3":
                selectedRoomType = SUITE;
                break;
        }
        Room roomToAdd = new Room(roomNumber,roomPrice,selectedRoomType);
        roomData.put(roomToAdd.getRoomNumber(),roomToAdd);
    }
    public static IRoom getARoom(String roomId){
        return roomData.get(roomId);
    }

    public static void reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation myRes = new Reservation(customer, room, checkInDate, checkOutDate);
        reservationData.add(myRes);
    }

    public static Map<String, IRoom> findRooms(Date checkInDate, Date checkOutDate){
        Map<String, IRoom> availableRooms = new HashMap<>(roomData);
        Iterator<Reservation> iterator = reservationData.iterator();
        while (iterator.hasNext()) {
            Reservation activeResy = iterator.next();
            if(activeResy.getCheckInDate().before(checkOutDate) && activeResy.getCheckOutDate().after(checkInDate)){
                availableRooms.remove(activeResy.getRoom().getRoomNumber());
            }
        }
        System.out.println(availableRooms);
        return availableRooms;
    }

    public static Collection<Reservation> getCustomersReservation(Customer customer){
        Collection<Reservation> matchedReservations = new ArrayList<>();
        Iterator<Reservation> iterator = reservationData.iterator();
        while (iterator.hasNext()) {
            Reservation activeResy = iterator.next();
            if(activeResy.getCustomer().getEmail() == customer.getEmail()){
                matchedReservations.add(activeResy);
            }
        }
        return matchedReservations;
    }

    public static List<Reservation> getAllReservations(){
        return reservationData;

    }

    public static Collection<IRoom> getAllRooms(){
        return roomData.values();
    }
}
