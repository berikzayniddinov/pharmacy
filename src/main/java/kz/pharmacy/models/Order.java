package kz.pharmacy.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order extends Entity implements Printable{
    private int customerId;
    private Date orderDate;
    private double totalAmount;

    @Override
     public void printInfo() {
    System.out.println(id + ": Customer ID - " + customerId + ", Order Date - " + orderDate + ", Total Amount - $" + totalAmount);
    }
}
