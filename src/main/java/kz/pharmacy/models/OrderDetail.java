package kz.pharmacy.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetail extends Entity implements Printable{
    private int orderId;
    private int medicineId;
    private int quantity;
    private double subtotal;

    @Override
     public void printInfo() {
        System.out.println( id + ": Order ID - " + orderId + ", Medicine ID - "
                + medicineId + ", Quantity - " + quantity + ", Subtotal - $" + subtotal);
    }
}
