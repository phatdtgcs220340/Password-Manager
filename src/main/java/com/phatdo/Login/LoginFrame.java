package com.phatdo.Login;

import com.phatdo.Authentication.Authentication;
import com.phatdo.DataProcess.ConnectDatabase;
import com.phatdo.PasswordManager.ErrorMessage;
import org.postgresql.util.PSQLException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class LoginFrame extends JFrame {

    private JLabel loginLabel, usernameLabel, passwordLabel;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginFrame() {
        // Set the frame properties
        setTitle("Login Application");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        loginLabel = new JLabel("Login");
        usernameLabel = new JLabel("Username");
        passwordLabel = new JLabel("Password");
        usernameTextField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        // Set password field to hide characters
        passwordField.setEchoChar('*');

        // Set layout manager
        setLayout(new GridLayout(4, 2));

        // Add components to the frame
        add(loginLabel);
        add(new JLabel()); // Empty label for spacing
        add(usernameLabel);
        add(usernameTextField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel()); // Empty label for spacing
        add(loginButton);


        // Add action listener for the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    performLogin();
                }
            }
        });
    }
    private void performLogin(){
        String username = usernameTextField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);
        try {
            if (ConnectDatabase.checkAuthenticate(username, password)) {
                Authentication.setAuthenticate(true);
                System.out.println("ok");
            }
            else {
                ErrorMessage.showErrorDialog("Couldn't find user or wrong password", "User error");
            }
        }
        catch (SQLException e) {
            ErrorMessage.showErrorDialog("Coudln't connect to database :((", "Connection error");
        }
    }

}
