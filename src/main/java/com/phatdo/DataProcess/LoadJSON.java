package com.phatdo.DataProcess;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phatdo.Cryptography.CryptographyTest;

import java.sql.*;
import java.time.Instant;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class LoadJSON {
    public static void main(String[] args) {
        try {
            // Specify the path to your JSON file
            String jsonFilePath = "src/main/resources/data/pass.json";

            // Create an ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Read JSON file into JsonNode
            JsonNode jsonNode = objectMapper.readTree(new File(jsonFilePath));

            Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
            // Define database connection parameters
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String username = "postgres";
            String password = "dotanphat";

            // Define table name
            String tableName = "applications";

            // Connect to database
            try(Connection conn = DriverManager.getConnection(url, username, password)) {

            // Prepare SQL statement
            String sql = "INSERT INTO " + tableName + " (owner_id, application_id, application, username, password, date_modified) VALUES (?, ?, ?, ?, ?, ?)";

            // Create a PreparedStatement
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Set values for each column
            int ownerId = 1; // Replace with your actual owner ID
            int applicationId = 1;




            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                String application = entry.getKey();
                JsonNode applicationDetails = entry.getValue();

                // Extract details for each application
                String userName = applicationDetails.get("Username").asText();
                String user_password = applicationDetails.get("Password").asText();
                String user_password_decrypted = CryptographyTest.encrypt(user_password);
                Instant now = Instant.now();

                stmt.setInt(1, ownerId);
                stmt.setInt(2, applicationId++);
                stmt.setString(3, application);
                stmt.setString(4, userName);
                stmt.setString(5, user_password_decrypted);
                stmt.setTimestamp(6, Timestamp.from(now));

                // Execute the statement
                stmt.executeUpdate();
                // Print application details
                System.out.println("Application: " + application);
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);
                System.out.println("Date modified: " + now);
                System.out.println();
            }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
