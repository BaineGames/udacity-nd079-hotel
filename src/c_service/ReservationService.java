package c_service;

import d_model.*;

import java.util.*;

import static d_model.RoomType.*;

public class ReservationService {

    private final Set<IRoom> roomData;
    private final Map<String, ArrayList<Reservation>> reservationData;
    private static ReservationService instance = null;
    private ReservationService(){
        roomData = new HashSet<>();
        reservationData = new HashMap<>();
    };
    public static ReservationService getInstance(){
        if(instance == null){instance = new ReservationService();}
        return instance;
    }
    public void addRoom(String roomNumber, double roomPrice, String roomType){
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

        Room roomToAdd = new Room(roomNumber, roomPrice, selectedRoomType);

        boolean doesRoomExistAlready = false;

        Set<IRoom> tempRoomSet = new HashSet<>(roomData);
        //loop through temp list to see if room is used already
        for(IRoom roomSearch: tempRoomSet){
            if(roomSearch.getRoomNumber().equals(roomToAdd.getRoomNumber())){
                doesRoomExistAlready = true;
            }
        }

        if(doesRoomExistAlready) {
            System.out.println("Room exists, try again");
        }else{
            roomData.add(roomToAdd);
        }
    }
    public IRoom getARoom(String roomId){
        for(IRoom roomToFind: roomData){
            if(roomToFind.getRoomNumber().equals(roomId)){
                return roomToFind;
            }
        }
        return null;
    }

    public void reserveARoom(Customer customer, IRoom room, Date checkIn, Date checkOut){
        Reservation myNewReservation = new Reservation(customer, room, checkIn, checkOut);
        ArrayList<Reservation> tempArray = new ArrayList<>();
        reservationData.putIfAbsent(customer.getEmail(), tempArray);
        reservationData.get(customer.getEmail()).add(myNewReservation);
    }

    public List<IRoom> findRooms(Date checkInDate, Date checkOutDate){
        List<IRoom> availableRooms = new ArrayList<>(roomData);
        reservationData.forEach((s,list) -> {
            Iterator<Reservation> iterator = list.iterator();
            while (iterator.hasNext()) {
                Reservation activeResy = iterator.next();
                Date activeResyCheckIn = activeResy.getCheckInDate();
                Date activeResyCheckOut = activeResy.getCheckOutDate();

                //boundary tests to remove room from list - probably overkill
                if(activeResyCheckIn.after(checkInDate) && activeResyCheckIn.before(checkOutDate)){
                    //this should mean the checkin for the room is
                    // between my selected dates, which means its not available for use
                    availableRooms.remove(activeResy.getRoom());
                }
                if(activeResyCheckOut.after(checkInDate) && activeResyCheckOut.before(checkOutDate)){
                    //this should mean the checkout date for the room is
                    //between my selected dates, which means its not available for use
                    availableRooms.remove(activeResy.getRoom());
                }
                if(activeResyCheckIn.before(checkInDate) && activeResyCheckOut.after(checkOutDate)) {
                    //this should mean the checkin and checkout for the existing reservation is
                    // includes my selected dates, which means its not available for use
                    availableRooms.remove(activeResy.getRoom());
                }
                if(activeResyCheckIn.equals(checkInDate) && activeResyCheckOut.equals(checkOutDate)){
                    //this should mean the reservation matches my exact dates, so nope
                    availableRooms.remove(activeResy.getRoom());
                }
                if(activeResyCheckIn.equals(checkInDate)){
                    //if any reservation has the same checkin date - we know it shouldnt be available
                    availableRooms.remove(activeResy.getRoom());
                }
                if(activeResyCheckOut.equals(checkOutDate)){
                    //if any reservation has the same checkout date - we know it shouldnt be available
                    availableRooms.remove(activeResy.getRoom());
                }
            }
        });

        return  availableRooms;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer){
        try{
        return reservationData.get((customer.getEmail()));
        }catch(NullPointerException ex){
            System.out.println("no reservations found");
        }
    return null;
    }

    public void getAllReservations(){
        System.out.println(reservationData);

    }

    public  Collection<IRoom> getAllRooms(){
        return roomData;
    }
}
