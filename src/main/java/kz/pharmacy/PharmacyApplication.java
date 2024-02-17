package kz.pharmacy;
//importing necessary classes and packages
import kz.pharmacy.connector.DatabaseConnector;
import kz.pharmacy.models.Customer;
import kz.pharmacy.models.Medicine;
import kz.pharmacy.service.CustomerService;
import kz.pharmacy.service.MedicineService;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;



public class PharmacyApplication {
    public static void main(String[] args) throws SQLException {
        System.out.println("PharmacyApplication is running\n");
        //Creating Scanner object for input
        try (Scanner scanner = new Scanner(System.in)) {

            try {
                boolean running = true;
                while (running) {
                    //Displaying main menu options
                    System.out.println("Select an option:");
                    System.out.println("1: Add new customer");
                    System.out.println("2: Add new medicine");
                    System.out.println("3, See list of all medicines");

                    System.out.println("4: Register new customer");
                    System.out.println("5: Login");
                    System.out.println("6: Exit");
                    // Reading user input for option selection
                    int option = scanner.nextInt();
                    switch (option) {
                        case 1:
                            addNewCustomer(scanner);
                            break;
                        case 2:
                            addNewMedicine(scanner);
                            break;

                        case 3:
                            getAllMedicines();
                            break;

                        case 4:
                            registerNewCustomer(scanner);
                            break;
                        case 5:
                            login(scanner);
                        case 6:
                        System.out.println("Exiting...");
                            running = false;
                            break;
                        default:
                            System.out.println("Invalid option selected");
                            break;
                    }
                }
            } catch (SQLException e) {
                // Handling SQLException thrown during database operations
                System.out.println("PharmacyApplication Error: " + e.getMessage());
            } finally {
                // Closing database connection and scanner
                if (DatabaseConnector.getConnection() != null) {
                    try {
                        DatabaseConnector.getConnection().close();
                    } catch (SQLException e) {
                        System.out.println("Could not close the connection: " + e.getMessage());
                    }
                }
                scanner.close();
                System.out.println("\nPharmacyApplication is stopped");
            }
        }
    }

    // Method to add a new customer
    private static void addNewCustomer(Scanner scanner) throws SQLException {
        // Prompting user to enter customer details
        System.out.println("Enter first name:");
        String firstName = scanner.next();
        System.out.println("Enter last name:");
        String lastName = scanner.next();
        System.out.println("Enter address:");
        scanner.nextLine();
        String address = scanner.nextLine();
        System.out.println("Enter phone number:");
        String phoneNumber = scanner.next();



        // Creating a new Customer object with input data
        Customer newCustomer = new Customer();
        newCustomer.setFirstName(firstName);
        newCustomer.setLastName(lastName);
        newCustomer.setAddress(address);
        newCustomer.setPhoneNumber(phoneNumber);
        // Creating CustomerService instance and adding the new customer
        CustomerService customerService = new CustomerService(DatabaseConnector.getConnection());
        if (customerService.addCustomer(newCustomer)) {
            System.out.println("New customer added successfully.");
        } else {
            System.out.println("Failed to add new customer.");
        }
    }
    // Method to add a new medicine
    private static void addNewMedicine(Scanner scanner) throws SQLException {
        // Prompting user to enter medicine details
        System.out.println("Enter medicine name:");
        String name = scanner.next();
        System.out.println("Enter manufacturer:");
        scanner.nextLine();
        String manufacturer = scanner.nextLine();
        System.out.println("Enter dosage:");
        String dosage = scanner.next();
        System.out.println("Enter form:");
        String form = scanner.next();
        System.out.println("Enter price:");
        double price = scanner.nextDouble();

        // Creating a new Medicine object with input data
        Medicine newMedicine = new Medicine();
        newMedicine.setName(name);
        newMedicine.setManufacturer(manufacturer);
        newMedicine.setDosage(dosage);
        newMedicine.setForm(form);
        newMedicine.setPrice(price);

        // Creating MedicineService instance and adding the new medicine
        MedicineService medicineService = new MedicineService(DatabaseConnector.getConnection());
        if (medicineService.addMedicine(newMedicine)) {
            System.out.println("New medicine added successfully.");
        } else {
            System.out.println("Failed to add new medicine.");
        }
    }
        private static void getAllMedicines() throws SQLException {
            MedicineService medicineService = new MedicineService(DatabaseConnector.getConnection());
            List<Medicine> medicines = medicineService.getAllMedicines();
            System.out.println("List of all medicines:");
            for (Medicine medicine : medicines) {
                medicine.printInfo();
            }
        }






    private static void registerNewCustomer(Scanner scanner) throws SQLException {
        // Prompting user to enter customer details
        System.out.println("Enter first name:");
        String firstName = scanner.next();
        System.out.println("Enter last name:");
        String lastName = scanner.next();


        // Creating a new Customer object with input data
        Customer newCustomer = new Customer();
        newCustomer.setFirstName(firstName);
        newCustomer.setLastName(lastName);



        // Creating CustomerService instance and registering the new customer
        CustomerService customerService = new CustomerService(DatabaseConnector.getConnection());
        if (customerService.registerCustomer(newCustomer.getFirstName(), newCustomer.getLastName())) {
            System.out.println("New customer registered successfully.");
        } else {
            System.out.println("Failed to register new customer.");
        }
    }

    private static void login(Scanner scanner) throws SQLException {
        System.out.println("Enter username:");
        String username = scanner.next();

        CustomerService customerService = new CustomerService(DatabaseConnector.getConnection());
        if (customerService.loginCustomer(username)) {
            System.out.println("Login successful.");
        } else {
            System.out.println("Invalid username.");
        }
    }


}
