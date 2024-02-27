package kz.pharmacy.models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// // Annotations for generating constructors, getters, setters, and providing default constructor
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Customer extends  Entity implements Printable {
    private int customer_id;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;


    @Override
    public void printInfo() {
        System.out.println(customer_id + ": " + firstName + " " + lastName + " - " + address + ", " + phoneNumber);
    }
}
