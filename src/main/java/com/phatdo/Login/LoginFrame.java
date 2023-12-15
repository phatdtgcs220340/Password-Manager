package com.phatdo.Login;

import com.phatdo.Authentication.Authentication;
import com.phatdo.DataProcess.OwnerProcess;
import com.phatdo.PasswordManager.DialogMessage;
import com.phatdo.StringFormatter.StringFormat;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class LoginFrame extends JFrame {

    private JLabel loginLabel, usernameLabel, passwordLabel;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginFrame() {
        // Set the frame properties
        setTitle("Login Application");
        setSize(250, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        ImageIcon img = new ImageIcon("src/main/resources/img/icon/frameicon.png");
        setIconImage(img.getImage());
        getContentPane().setBackground(Color.decode("#EEF5FF"));

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
        loginButton.addActionListener(e -> performLogin());
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    performLogin();
                }
            }
        });

    }

    private void performLogin() {
        String username = StringFormat.removeLeadingTrailingSpaces(usernameTextField.getText());
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);
        try {
            if (OwnerProcess.checkAuthenticate(username, password)) {
                Authentication.setAuthenticate(true);
                System.out.println("Authenticated");
            } else {
                DialogMessage.showErrorDialog("Dit me may ma doi vo coi acc tao", "User error");
            }
        } catch (SQLException e) {
            DialogMessage.showErrorDialog("Couldn't connect to database :((", "Connection error");
        }
    }
}
