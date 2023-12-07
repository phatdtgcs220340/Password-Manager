package com.phatdo.DataProcess;
import com.phatdo.Cryptography.CryptographyTest;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class ConnectDatabase {
    // JDBC URL, username, and password of PostgreSQL server
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "dotanphat";

    public static boolean checkAuthenticate(String username, String password) throws SQLException{
        try(Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);) {
            // Establish a connection
            // SQL query
            String sqlQuery = "SELECT * FROM owners";

            // Create a PreparedStatement
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                // Execute the query and obtain a ResultSet
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Process the ResultSet
                    while (resultSet.next()) {
                        String username_tmp = resultSet.getString("owner_name");
                        String password_tmp = CryptographyTest.decrypt(resultSet.getString("password"));
                        if (username_tmp.equals(username) && password_tmp.equals(password))
                            return true;
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return false;
        }
    }
    public static void findApplication(String expected_application) throws SQLException {
        try(Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);) {
            // Establish a connection

            // Your SQL query
            String sqlQuery = "SELECT * FROM applications " +
                    "WHERE application='"+expected_application+"';";

            // Create a PreparedStatement
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                // Execute the query and obtain a ResultSet
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Process the ResultSet
                    while (resultSet.next()) {
                        // Retrieve data from each row
                        int ownerId = resultSet.getInt("owner_id");
                        String application = resultSet.getString("application");
                        String username = resultSet.getString("username");
                        String password = resultSet.getString("password");
                        password = CryptographyTest.decrypt(password);
                        // Add more columns as needed

                        // Process the data as needed (e.g., print or use it)
                        System.out.println("Owner ID: " + ownerId);
                        System.out.println("Application: " + application);
                        System.out.println("Username: " + username);
                        System.out.println("Password: " + password);
                        // Print or use additional columns

                        System.out.println("----------");
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    }

