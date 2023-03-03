package c_service;

import d_model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {

    private static final Map<String, Customer> customersData = new HashMap<>();
    public static void addCustomer(String firstName, String lastName, String email){
        customersData.put(email, new Customer(firstName, lastName, email));
    }

    public static Customer getCustomer(String customerEmail){
        return customersData.get(customerEmail);
    }

    public static Collection<Customer> getAllCustomers(){
        return customersData.values();
    }

}
