package kz.pharmacy.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicineCategory extends Entity implements Printable{
    private String name;

    @Override
     public void printInfo() {
    System.out.println(id + ": " + name);
    }
}
