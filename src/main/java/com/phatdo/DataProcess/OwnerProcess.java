package com.phatdo.DataProcess;

import com.phatdo.Cryptography.Cryptography;

import java.sql.*;

public class OwnerProcess {
    // JDBC URL, username, and password of PostgreSQL server
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = System.getenv("postgres_user");

    private static final String PASSWORD = System.getenv("postgres_pwd");

    private static int user_id;

    public static void setUser_id(int user_id) {
        OwnerProcess.user_id = user_id;
    }

    public static int getUser_id() {
        return OwnerProcess.user_id;
    }

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
                String password_tmp = Cryptography.decrypt(resultSet.getString("password"));
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

}
