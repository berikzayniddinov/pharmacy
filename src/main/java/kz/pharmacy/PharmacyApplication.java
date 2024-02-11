package kz.pharmacy;

import kz.pharmacy.connector.DatabaseConnector;
import kz.pharmacy.models.Customer;
import kz.pharmacy.models.Medicine;
import kz.pharmacy.service.CustomerService;
import kz.pharmacy.service.MedicineService;
import kz.pharmacy.service.OrderService;

import java.sql.SQLException;
import java.util.List;

public class PharmacyApplication {
    public static void main(String[] args) throws SQLException {
        System.out.println("PharmacyApplication is running\n");

        try {

            System.out.println("Medicines start <---------------------------------------->\n");

            // Creating medicineService object for working with medicines
            MedicineService medicineService = new MedicineService(DatabaseConnector.getConnection());

            // Getting all medicines list
            List<Medicine> medicines = medicineService.getAllMedicines();

            // Print info about medicines
            System.out.println("Medicines:");
            for (Medicine medicine : medicines) {
                medicine.printInfo();
            }

            System.out.println("\nMedicines end <---------------------------------------->\n");

            System.out.println("Customers start <---------------------------------------->\n");

            // Creating customerService object for working with customers
            CustomerService customerService = new CustomerService(DatabaseConnector.getConnection());


            // Getting all  customers list
            List<Customer> customers = customerService.getAllCustomers();

            // Print info about customers
            System.out.println("Customers:");
            for (Customer customer : customers) {
                customer.printInfo();
            }

            System.out.println("\nCustomers end <---------------------------------------->\n");


            System.out.println("\nOrders start <---------------------------------------->\n");

            // Creating orderService object for working with orders
            OrderService orderService = new OrderService(DatabaseConnector.getConnection());

            //Print info about orders
            orderService.printOrderDetailsInfo();


            System.out.println("\nOrders end <---------------------------------------->\n");

        } catch (SQLException e) {
            System.out.println("PharmacyApplication Error: " + e.getMessage());
        } finally {
            if (DatabaseConnector.getConnection() != null) {
                try {
                    System.out.println("Database Connection is not null: closing");
                    DatabaseConnector.getConnection().close();
                    System.out.println("Database Connection is not null: closed");
                } catch (SQLException e) {
                    System.out.println("Could not close the connection: " + e.getMessage());
                }
            }


            System.out.println("\nPharmacyApplication is stopped");
        }
    }
}
