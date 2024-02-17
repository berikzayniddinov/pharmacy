package kz.pharmacy.service;

import kz.pharmacy.models.Customer;
import kz.pharmacy.models.Medicine;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
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

    public boolean loginCustomer(String username) {
        String query = "SELECT COUNT(*) FROM customers WHERE first_name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean registerCustomer(String firstName, String lastName) {
        String query = "INSERT INTO customers (first_name, last_name) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);


            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    }


