package com.example;  // Pastikan ini sesuai dengan package yang kamu gunakan.

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        // Hardcoded credentials (should be avoided)
        String username = "admin";  // Hardcoded username
        String password = "password123";  // Hardcoded password

        // Simulate a SQL Injection vulnerability
        String sqlQuery = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";

        // Example of calling a vulnerable method that can be attacked by SQL injection
        login(sqlQuery);

        // Calling a function that could throw another vulnerability (method to list users)
        List<String> users = getUsersFromDatabase();
        System.out.println("Users in the system: " + users);
    }

    private static void login(String query) {
        // Simulate SQL query execution with potential SQL Injection vulnerability
        try {
            // Database connection using hardcoded credentials (should use external config)
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "password");

            // Statement using unsafe concatenation (SQL Injection vulnerability)
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                System.out.println("User: " + rs.getString("username"));
            }
        } catch (Exception e) {
            System.out.println("Error during login: " + e.getMessage());
        }
    }

    // Function to simulate another risky part of the code
    private static List<String> getUsersFromDatabase() {
        List<String> users = new ArrayList<>();
        
        // Hardcoded list of users (could be a source of vulnerabilities)
        users.add("admin");
        users.add("user1");
        users.add("user2");

        // Simulating a dangerous API call or further vulnerability
        String apiUrl = "http://api.example.com/getUsers";
        System.out.println("Making an insecure API call to: " + apiUrl);

        return users;
    }
}
