package kz.pharmacy.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Annotations
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Medicine extends Entity implements Printable {
    private String name;
    private String manufacturer;
    private String dosage;
    private String form;
    private double price;
    private int categoryId;

    @Override
    public void printInfo() {
        System.out.println("Medicine ID -  " + id + "; Name -  " + name + "; Manufacturer -  "
                + manufacturer + "; Dosage -  " + dosage + "; Form -  " + form
                + "; Price -  $" + price + "; Category ID -  " + categoryId + "; ");
    }

}
