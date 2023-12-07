package com.phatdo.PasswordManager;

import javax.swing.*;

public class SearchFrame extends JFrame {
    private JLabel applicationLabel;
    private JTextField applicationTextField;
    private JTextField information;
    public SearchFrame() {
        setTitle("Search for Application");
        setSize(500,180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        applicationLabel = new JLabel("Application");
        applicationTextField = new JTextField();

    }
}
