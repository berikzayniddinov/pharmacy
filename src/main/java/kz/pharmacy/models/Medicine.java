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
public class Medicine extends Entity implements Printable {
    // Fields representing properties of a medicine
    private int medicine_id;
    private String name;
    private String manufacturer;
    private String dosage;
    private String form;
    private double price;

    // Implementation of the printInfo method from the Printable interface
    @Override
    public void printInfo() {
        System.out.println("Medicine ID -  " + medicine_id + "; Name -  " + name + "; Manufacturer -  "
                + manufacturer + "; Dosage -  " + dosage + "; Form -  " + form
                + "; Price -  $" + price + "; ");
    }

}
