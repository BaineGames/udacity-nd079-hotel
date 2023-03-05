package d_model;

import java.util.regex.Pattern;

public class Customer {
    private String firstName;
    private String lastName;
    public String email;

    public String getEmail() {
        return email;
    }

    public Customer(String firstName, String lastName, String email) {
        try {
            if (firstName == null || lastName == null || email == null) {
                throw new IllegalArgumentException("Customer information cannot be null");
            }
            String emailRegex = "^(.+)@(.+)[.](.+)$";
            Pattern pattern = Pattern.compile(emailRegex);
            if(!pattern.matcher(email).matches()){
                throw new IllegalArgumentException("Email not valid format");
            }
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }catch(IllegalArgumentException ex){
            System.out.println(ex.getLocalizedMessage());
        }
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + email + ")";
    }
}
