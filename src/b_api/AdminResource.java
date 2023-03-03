package b_api;

import c_service.CustomerService;
import d_model.Customer;
import d_model.IRoom;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    public Customer getCustomer(String email){
        return CustomerService.getCustomer(email);
    }
    public void addRoom(List<IRoom> rooms){}

    public Collection<IRoom> getAllRooms(){
        return null;
    }

    public static Collection<Customer> getAllCustomers(){

        return CustomerService.getAllCustomers();
    }

    public void displayAllReservations(){}
}
