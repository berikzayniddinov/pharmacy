package kz.pharmacy.service;

import kz.pharmacy.models.Customer;
import kz.pharmacy.models.Medicine;
import kz.pharmacy.models.Order;
import kz.pharmacy.models.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private Connection connection;

    public OrderService(Connection connection) {
        this.connection = connection;
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try {
            String query = "SELECT * FROM orders";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Order order = new Order();
                    order.setId(resultSet.getInt("order_id"));
                    order.setCustomerId(resultSet.getInt("customer_id"));
                    order.setOrderDate(resultSet.getDate("order_date"));
                    order.setTotalAmount(resultSet.getDouble("total_amount"));
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        try {
            String query = "SELECT * FROM order_details WHERE order_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, orderId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setId(resultSet.getInt("detail_id"));
                    orderDetail.setOrderId(resultSet.getInt("order_id"));
                    orderDetail.setMedicineId(resultSet.getInt("medicine_id"));
                    orderDetail.setQuantity(resultSet.getInt("quantity"));
                    orderDetail.setSubtotal(resultSet.getDouble("subtotal"));
                    orderDetails.add(orderDetail);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetails;
    }

    public void printOrderDetailsInfo() {
        List<Order> orders = getAllOrders();
        for (Order order : orders) {
            System.out.println("Order ID: " + order.getId());
            System.out.println("Order Date: " + order.getOrderDate());
            System.out.println("Total Amount: $" + order.getTotalAmount());

            // Getting customer by id
            CustomerService customerService = new CustomerService(connection);
            Customer customer = customerService.getCustomerById(order.getCustomerId());

            System.out.println("Customer: " + customer.getFirstName() + " " + customer.getLastName());
            System.out.println("Address: " + customer.getAddress());
            System.out.println("Phone Number: " + customer.getPhoneNumber());

            List<OrderDetail> orderDetails = getOrderDetailsByOrderId(order.getId());
            System.out.println("Order Details:");

            for (OrderDetail orderDetail : orderDetails) {

                // Getting medicine by id
                MedicineService medicineService = new MedicineService(connection);
                Medicine medicine = medicineService.getMedicineById(orderDetail.getMedicineId());

                System.out.println("  ------------------");

                System.out.println("  Detail ID: " + orderDetail.getId());
                System.out.println("  Medicine: " + medicine.getName() + " (" + medicine.getManufacturer() + ")");
                System.out.println("  Dosage: " + medicine.getDosage());
                System.out.println("  Form: " + medicine.getForm());
                System.out.println("  Quantity: " + orderDetail.getQuantity());
                System.out.println("  Subtotal: $" + orderDetail.getSubtotal());
            }

            System.out.println("<---------------------------------------->");
        }
    }

}
