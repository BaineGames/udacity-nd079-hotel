package c_service;

import d_model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {

    private static final Map<String, Customer> customersData = new HashMap<>();

    public static void addCustomer(String firstName, String lastName, String email){
        Customer customerToAdd = new Customer(firstName, lastName, email);
        if(customerToAdd.getEmail() != null){
            customersData.put(email, customerToAdd);
        }
    }

    public static Customer getCustomer(String customerEmail){
        return customersData.get(customerEmail);
    }

    public static Collection<Customer> getAllCustomers(){
        return customersData.values();
    }

}
