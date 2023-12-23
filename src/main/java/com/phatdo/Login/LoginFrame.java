package com.phatdo.Login;

import com.phatdo.Authentication.Authentication;
import com.phatdo.ColorFormatter.ColorEnum;
import com.phatdo.DataProcess.OwnerProcess;
import com.phatdo.PasswordManager.DialogMessage;
import com.phatdo.StringFormatter.StringFormat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class LoginFrame extends JFrame {
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginFrame() {
        // Set the frame properties
        setTitle("Login Application");
        setSize(250, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        ImageIcon img = new ImageIcon("src/main/resources/img/frame_icon/frameicon.png");
        setIconImage(img.getImage());
        // Create components
        usernameTextField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        // Set password field to hide characters
        passwordField.setEchoChar('*');

        // Set layout manager
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Add components to the frame with GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(new JLabel("Username:"), gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(usernameTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(new JLabel("Password:"), gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(loginButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        add(registerButton, gbc);

        // Add action listener for the login button
        loginButton.addActionListener(e -> performLogin());
        registerButton.addActionListener(e -> performRegister());
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

    private void performRegister() {
        JFrame registerFrame = new RegisterFrame();
        registerFrame.setVisible(true);
    }
}
