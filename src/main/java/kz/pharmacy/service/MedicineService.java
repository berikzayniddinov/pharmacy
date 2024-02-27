package kz.pharmacy.service;
//importing necessary classes and packages
import kz.pharmacy.models.Medicine;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Service class for handling Medicine related operations
public class MedicineService {
    private Connection connection;
    // Constructor to initialize MedicineService with a database connection
    public MedicineService(Connection connection) {
        this.connection = connection;
    }
    // Method to add a new medicine to the database
    public boolean addMedicine(Medicine medicine) {
        // SQL query to insert medicine data into the database
        String query = "INSERT INTO medicines (medicine_id, name, manufacturer, dosage, form, price) VALUES (?, ?, ?, ?, ?, ?)";
        // Setting parameters for the prepared statement
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, medicine.getMedicine_id());
            preparedStatement.setString(2, medicine.getName());
            preparedStatement.setString(3, medicine.getManufacturer());
            preparedStatement.setString(4, medicine.getDosage());
            preparedStatement.setString(5, medicine.getForm());
            preparedStatement.setDouble(6, medicine.getPrice());


            // Executing the update query
            int affectedRows = preparedStatement.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        }
        public List<Medicine> getAllMedicines() {
            List<Medicine> Medicine = new ArrayList<>();
             String query = "SELECT * FROM medicines";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Medicine medicine = new Medicine();
                    medicine.setMedicine_id(resultSet.getInt("medicine id"));
                    medicine.setName(resultSet.getString("name"));
                    medicine.setManufacturer(resultSet.getString("manufacturer"));
                    medicine.setDosage(resultSet.getString("dosage"));
                    medicine.setForm(resultSet.getString("form"));
                    medicine.setPrice(resultSet.getDouble("price"));
                    medicine.printInfo();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return Medicine;
        }



}




