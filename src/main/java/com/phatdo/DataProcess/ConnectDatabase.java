package com.phatdo.DataProcess;
import com.phatdo.Cryptography.CryptographyTest;

import java.sql.*;
import java.time.Instant;

public class ConnectDatabase {
    // JDBC URL, username, and password of PostgreSQL server
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = System.getenv("postgres_user");

    private static final String PASSWORD = System.getenv("postgres_pwd");

    private static int user_id;

    public static boolean checkAuthenticate(String username, String password) throws SQLException {
        // SQL query
        String sqlQuery = "SELECT * FROM owners";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static String findApplication(String expected_application) throws SQLException {
        String result = "";
        String sqlQuery = String.format("SELECT * FROM applications " +
                "WHERE application= '%s' AND owner_id= %d ;", expected_application, user_id);
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
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
        return result;
    }

    public static void addApplication(String application, String username, String password) throws Exception {
        String sqlQuery = String.format("INSERT INTO applications (owner_id, application, username, password, date_modified)" +
                "VALUES (%d, '%s', '%s', '%s', '%s');", user_id, application, username, CryptographyTest.encrypt(password), Timestamp.from(Instant.now()));
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.executeUpdate();
        }
    }
}

