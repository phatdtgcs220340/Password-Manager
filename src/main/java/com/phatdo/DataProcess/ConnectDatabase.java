package com.phatdo.DataProcess;
import com.phatdo.Cryptography.CryptographyTest;

import java.sql.*;

public class ConnectDatabase {
    // JDBC URL, username, and password of PostgreSQL server
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "dotanphat";

    private static int user_id;
    public static void main(String[] args) {
        try {
            System.out.println(findApplication("Udemy"));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
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
                        int user_id_tmp = resultSet.getInt("owner_id");
                        String username_tmp = resultSet.getString("owner_name");
                        String password_tmp = CryptographyTest.decrypt(resultSet.getString("password"));
                        if (username_tmp.equals(username) && password_tmp.equals(password)) {
                            user_id = user_id_tmp;
                            return true;
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return false;
        }
    }
    public static String findApplication(String expected_application) throws SQLException {
        String result = "";
        try(Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);) {
            // Establish a connection

            // Your SQL query
            String sqlQuery = String.format("SELECT * FROM applications " +
                    "WHERE application= '%s' AND owner_id= %d ;", expected_application, user_id);

            // Create a PreparedStatement
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                // Execute the query and obtain a ResultSet
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int ownerId = resultSet.getInt("owner_id");
                        String application = resultSet.getString("application");
                        String username = resultSet.getString("username");
                        String password = resultSet.getString("password");
                        password = CryptographyTest.decrypt(password);
                        result = String.format("%s\nUsername: %s\nPassword: %s", application, username, password);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return result;
    }
    }

