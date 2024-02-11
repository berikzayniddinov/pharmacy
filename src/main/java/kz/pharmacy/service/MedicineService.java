package kz.pharmacy.service;

import kz.pharmacy.models.Medicine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicineService {
    private Connection connection;

    public MedicineService(Connection connection) {
        this.connection = connection;
    }

    public Medicine getMedicineById(int medicineId) {
        Medicine medicine = null;
        try {
            String query = "SELECT * FROM medicines WHERE medicine_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, medicineId);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    medicine = new Medicine();
                    medicine.setId(resultSet.getInt("medicine_id"));
                    medicine.setName(resultSet.getString("name"));
                    medicine.setManufacturer(resultSet.getString("manufacturer"));
                    medicine.setDosage(resultSet.getString("dosage"));
                    medicine.setForm(resultSet.getString("form"));
                    medicine.setPrice(resultSet.getDouble("price"));
                    medicine.setCategoryId(resultSet.getInt("category_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicine;
    }
    public List<Medicine> getAllMedicines() {
        List<Medicine> medicines = new ArrayList<>();
        try {
            String query = "SELECT * FROM medicines";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Medicine medicine = new Medicine();
                    medicine.setId(resultSet.getInt("medicine_id"));
                    medicine.setName(resultSet.getString("name"));
                    medicine.setManufacturer(resultSet.getString("manufacturer"));
                    medicine.setDosage(resultSet.getString("dosage"));
                    medicine.setForm(resultSet.getString("form"));
                    medicine.setPrice(resultSet.getDouble("price"));
                    medicine.setCategoryId(resultSet.getInt("category_id"));
                    medicines.add(medicine);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicines;
    }

}
