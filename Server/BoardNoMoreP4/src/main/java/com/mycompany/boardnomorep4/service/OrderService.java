package com.mycompany.boardnomorep4.service;

import com.mycompany.boardnomorep4.db.DatabaseConnector;
import com.mycompany.boardnomorep4.db.DatabaseUtils;
import com.mycompany.boardnomorep4.model.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class OrderService {
    
    private final static String ALL_ORDERS_QUERY = "SELECT * FROM orders";
    
    public static Order getOrderById(int id) {
        //Get a new connection object before going forward with the JDBC invocation.
        Connection connection = DatabaseConnector.getConnection();
        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_ORDERS_QUERY + " WHERE oid = " + id);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    Order order = new Order();

                    order.setOid(resultSet.getInt("oid"));
                    order.setGid(resultSet.getInt("gid"));
                    order.setQuantity(resultSet.getInt("quantity"));
                    order.setFname(resultSet.getString("fname"));
                    order.setLname(resultSet.getString("lname"));
                    order.setPhone_num(resultSet.getString("phone_num"));
                    order.setAddress(resultSet.getString("address"));
                    order.setCity(resultSet.getString("city"));
                    order.setState(resultSet.getString("state"));
                    order.setZip(resultSet.getInt("zip"));
                    order.setDelivery(resultSet.getString("delivery"));
                    order.setCredit(resultSet.getString("credit")); 

                    return order;

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {

                    // We will always close the connection once we are done interacting with the Database.
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
        
        
        } //end getOrderById
    
    public static List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();

        Connection connection = DatabaseConnector.getConnection();
        ResultSet resultSet = DatabaseUtils.retrieveQueryResults(connection, ALL_ORDERS_QUERY);

        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    Order order = new Order();

                    order.setOid(resultSet.getInt("oid"));
                    order.setGid(resultSet.getInt("gid"));
                    order.setQuantity(resultSet.getInt("quantity"));
                    order.setFname(resultSet.getString("fname"));
                    order.setLname(resultSet.getString("lname"));
                    order.setPhone_num(resultSet.getString("phone_num"));
                    order.setAddress(resultSet.getString("address"));
                    order.setCity(resultSet.getString("city"));
                    order.setState(resultSet.getString("state"));
                    order.setZip(resultSet.getInt("zip"));
                    order.setDelivery(resultSet.getString("delivery"));
                    order.setCredit(resultSet.getString("credit")); 

                    orders.add(order);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return orders;
    }

    public static boolean AddOrder(Order order) {

        String sql = "INSERT INTO orders  (gid, quantity, fname, lname, phone_num, address, city, state, zip, delivery, credit)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
         
        Connection connection = DatabaseConnector.getConnection();
        return DatabaseUtils.performDBUpdate(connection, sql, String.valueOf(order.getGid()), String.valueOf(order.getQuantity()), 
                order.getFname(), order.getLname(), order.getPhone_num(), order.getAddress(), order.getCity(), order.getState(), 
                String.valueOf(order.getZip()), order.getDelivery(), order.getCredit());

    }

    public static boolean updateOrder(Order order) {

        String sql = "UPDATE orders gid=?, quantity=?, fname=?, lname=?, phone_num=?, address=?, city=?," +
                "state=?, zip=?, delivery=?, credit=? WHERE oid=?;";

        Connection connection = DatabaseConnector.getConnection();

        boolean updateStatus = DatabaseUtils.performDBUpdate(connection, sql, String.valueOf(order.getGid()),
                String.valueOf(order.getQuantity()), order.getFname(), order.getLname(), order.getPhone_num(), order.getAddress(),
                order.getCity(), order.getState(), String.valueOf(order.getZip()), order.getDelivery(), order.getCredit(), 
                String.valueOf(order.getOid()));

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updateStatus;

    }

    public static boolean deleteOrder(Order order) {

        String sql = "DELETE FROM orders WHERE oid=?;";

        Connection connection = DatabaseConnector.getConnection();

        boolean updateStatus = DatabaseUtils.performDBUpdate(connection, sql, String.valueOf(order.getGid()));

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updateStatus;
    }
    
}
