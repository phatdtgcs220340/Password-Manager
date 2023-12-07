package com.phatdo.PasswordManager;

import javax.swing.*;
import java.awt.*;

public class PasswordManagerFrame extends JFrame {
    private JLabel applicationLabel;
    private JTextField applicationTextField;
    private JLabel userNameLabel;
    private JTextField userNameTextField;
    private JLabel passwordLabel;
    private JTextField passwordTextField;

    private JButton searchButton;
    private JButton addButton;
    private JButton deleteButton;
    public PasswordManagerFrame() {
        setTitle("Password Manager");
        setSize(700, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        applicationLabel = new JLabel("Application");
        applicationTextField = new JTextField();
        userNameLabel = new JLabel("Username");
        userNameTextField = new JTextField();
        passwordLabel = new JLabel("Password");
        passwordTextField = new JTextField();
        searchButton = new JButton("Search");
        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        setLayout(new GridLayout(4, 3));

        add(new JLabel());
        add(applicationLabel);
        add(applicationTextField);

        add(new JLabel());
        add(userNameLabel);
        add(userNameTextField);

        add(new JLabel());
        add(passwordLabel);
        add(passwordTextField);

        add(searchButton);
        add(addButton);
        add(deleteButton);
    }
}
