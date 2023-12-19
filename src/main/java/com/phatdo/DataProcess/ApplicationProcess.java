package com.phatdo.DataProcess;

import com.phatdo.ClipboardProcess.AutoCopy;
import com.phatdo.Cryptography.Cryptography;
import com.phatdo.StringFormatter.StringFormat;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;

public class ApplicationProcess {
    // JDBC URL, username, and password of PostgreSQL server
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = System.getenv("postgres_user");
    private static final String PASSWORD = System.getenv("postgres_pwd");

    public static void main(String[] args) throws SQLException {
        for (String application : applicationList()) {
            System.out.println(application);
        }
    }

    public static String findApplication(String expected_application) throws SQLException {
        String result = "";
        String sqlQuery = String.format("SELECT * FROM applications " +
                "WHERE application= '%s' AND owner_id= %d ;", expected_application, OwnerProcess.getUser_id());
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String application = resultSet.getString("application");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                password = Cryptography.decrypt(password);
                AutoCopy.copyToClipboard(password);
                password = StringFormat.encodeText('*', password.length());
                result = String.format("%s\nUsername: %s\nPassword: %s", application, username, password);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static void addApplication(String application, String username, String password) throws Exception {
        String sqlQuery = String.format(
                "INSERT INTO applications (owner_id, application, username, password, date_modified)" +
                        "VALUES (%d, '%s', '%s', '%s', '%s');",
                OwnerProcess.getUser_id(), application, username, Cryptography.encrypt(password),
                Timestamp.from(Instant.now()));
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.executeUpdate();
        }
    }

    public static void updateApplication(String application, String password) throws Exception {
        String sqlQuery = String.format("UPDATE applications " +
                "SET password = '%s', date_modified = '%s' " +
                "WHERE application = '%s' AND owner_id = '%d';", Cryptography.encrypt(password),
                Timestamp.from(Instant.now()),
                application, OwnerProcess.getUser_id());
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.executeUpdate();
        }
    }

    public static void deleteApplication(String application) throws SQLException {
        String sqlQuery = String.format("DELETE FROM applications "
                + "WHERE application = '%s' AND owner_id = '%d';",
                application, OwnerProcess.getUser_id());
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.executeUpdate();
        }
    }

    public static ArrayList<String> applicationList() throws SQLException {
        ArrayList<String> applications = new ArrayList<>();
        String sqlQuery = String.format("SELECT application from applications " +
                "WHERE owner_id = '%s';", OwnerProcess.getUser_id());
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                applications.add(resultSet.getString("application"));
            }
            return applications;
        }
    }
}
