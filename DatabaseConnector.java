package com.mycompany.boardnomorep4.db;

import java.sql.*;


public class DatabaseConnector {


    private DatabaseConnector() {

    }

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
          //replace database_name, username, and password
            return DriverManager.getConnection("jdbc:mysql://localhost/database_name",
                    "username", "password");
        } catch (SQLException e) {
            System.out.println(e.getMessage());



        }

        return null;
    }


}
