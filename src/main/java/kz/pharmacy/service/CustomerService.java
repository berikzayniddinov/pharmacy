package kz.pharmacy.service;

import kz.pharmacy.models.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;


public class CustomerService {
    private Connection connection;

    public CustomerService(Connection connection) {
        this.connection = connection;
    }

    public boolean addCustomer(Customer customer) {
        String query = "INSERT INTO customers (first_name, last_name, address, phone_number) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setString(4, customer.getPhoneNumber());

            int affectedRows = preparedStatement.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
