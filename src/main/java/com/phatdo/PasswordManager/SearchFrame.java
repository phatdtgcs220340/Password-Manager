package com.phatdo.PasswordManager;

import com.phatdo.DataProcess.ConnectDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SearchFrame extends JFrame {
    private JLabel applicationLabel;
    private JTextField applicationTextField;
    private JTextArea informationTextField;
    private JButton findButton;

    public SearchFrame() {
        setTitle("Search for Application");
        setSize(400, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        applicationLabel = new JLabel("Application");
        applicationTextField = new JTextField();
        informationTextField = new JTextArea(3, 20);
        findButton = new JButton("Find");

        informationTextField.setLineWrap(true);
        informationTextField.setWrapStyleWord(true);

        // Set GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(applicationLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(applicationTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        add(new JLabel("Information"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(informationTextField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(findButton, gbc);

        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performFind();
            }
        });
    }

    public void performFind() {
        try {
            String result = ConnectDatabase.findApplication(applicationTextField.getText());
            informationTextField.setText(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
