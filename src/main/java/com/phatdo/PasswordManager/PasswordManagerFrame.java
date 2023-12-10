package com.phatdo.PasswordManager;

import com.phatdo.DataProcess.ConnectDatabase;
import com.phatdo.Cryptography.PasswordGenerator;
import com.phatdo.StringFormatter.StringFormat;
import org.postgresql.util.PSQLException;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Objects;

public class PasswordManagerFrame extends JFrame {
    private JTextField addApplicationTextField;
    private JTextField addUsernameTextField;
    private JTextField addPasswordTextField;
    private JButton addButton;
    private JButton generatePasswordButton_add;
    private JTextField searchApplicationTextField;
    private JTextArea searchInformationTextField;
    private JButton searchButton;


    private JTextField updateApplicationTextField;
    private JTextField updatePasswordTextField;
    private JButton updateButton;
    private JButton generatePasswordButton_update;

    public PasswordManagerFrame() {
        setTitle("Password Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();


        // Search Tab
        JPanel searchPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        searchApplicationTextField = new JTextField();
        searchInformationTextField = new JTextArea(3, 20);
        searchButton = new JButton("Search");

        searchInformationTextField.setLineWrap(true);
        searchInformationTextField.setWrapStyleWord(true);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        searchPanel.add(new JLabel("Application"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        searchPanel.add(searchApplicationTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        searchPanel.add(new JLabel("Information"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        searchPanel.add(searchInformationTextField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        searchPanel.add(searchButton, gbc);

        searchButton.addActionListener(e -> performSearch());

        // Add Tab
        JPanel addPanel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();

        addApplicationTextField = new JTextField();
        addUsernameTextField = new JTextField();
        addPasswordTextField = new JTextField();
        addButton = new JButton("Add");
        generatePasswordButton_add = new JButton("Generate Password");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        addPanel.add(new JLabel("Application: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addPanel.add(addApplicationTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        addPanel.add(new JLabel("Username:  "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addPanel.add(addUsernameTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        addPanel.add(new JLabel("Password:  "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addPanel.add(addPasswordTextField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        addPanel.add(generatePasswordButton_add, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        addPanel.add(addButton, gbc);

        generatePasswordButton_add.addActionListener(e -> performGeneratePassword(addPasswordTextField));

        addButton.addActionListener(e -> performAdd());

        // Update tab
        JPanel updatePanel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();

        updateApplicationTextField = new JTextField();
        updatePasswordTextField = new JTextField();
        updateButton = new JButton("Update");
        generatePasswordButton_update = new JButton("Generate Password");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        updatePanel.add(new JLabel("Application"), gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        updatePanel.add(updateApplicationTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        updatePanel.add(new JLabel("New Password  "), gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        updatePanel.add(updatePasswordTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        updatePanel.add(updateButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        updatePanel.add(generatePasswordButton_update, gbc);

        generatePasswordButton_update.addActionListener(e -> performGeneratePassword(updatePasswordTextField));
        updateButton.addActionListener(e -> performUpdate());
        // Add tabs to the tabbedPane
        tabbedPane.addTab("Add Application", addPanel);
        tabbedPane.addTab("Search Application", searchPanel);
        tabbedPane.addTab("Update Application", updatePanel);
        // Add tabbedPane to the frame
        add(tabbedPane);

        // Set up the frame
        setSize(400, 300);
        setVisible(true);
    }

    private JPanel createPanel(JLabel label, JComponent component) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.WEST);
        panel.add(component, BorderLayout.CENTER);
        return panel;
    }

    public void performSearch() {
        try {
            String applicationInput = StringFormat.toCapitalize(searchApplicationTextField.getText());
            String result = ConnectDatabase.findApplication(applicationInput);
            if (Objects.equals(result, "")) {
                DialogMessage.showErrorDialog("Application doesn't exist","Invalid input");
                return;
            }
            searchInformationTextField.setText(result);
        } catch (SQLException e) {
            DialogMessage.showErrorDialog("Couldn't connect to database :((", "Connection error");
        }
    }

    public void performAdd() {
        try {
            String application = addApplicationTextField.getText();
            application = StringFormat.toCapitalize(application);
            String username = addUsernameTextField.getText();
            String password = addPasswordTextField.getText();
            if (username.isEmpty() || password.isEmpty() || application.isEmpty()) {
                DialogMessage.showErrorDialog("All necessary information must be filled", "Invalid input");
                return;
            }
            ConnectDatabase.addApplication(application, username, password);
            DialogMessage.showNotificationDialog(String.format("%s has been successfully added", application), "Application added");
        }
        catch (PSQLException e) {
            DialogMessage.showErrorDialog("Application has already existed", "Unique constraint violated");
        }
        catch (SQLException e) {
            DialogMessage.showErrorDialog("Couldn't connect to database :((", "Connection error");
        }
        catch (Exception e) {
            DialogMessage.showErrorDialog("Couldn't get Key", "Key Error");
        }
    }
    public void performUpdate() {
        try {
            String application = updateApplicationTextField.getText();
            application = StringFormat.toCapitalize(application);
            String password = updatePasswordTextField.getText();
            String result = ConnectDatabase.findApplication(application);
            if (Objects.equals(result, "")) {
                DialogMessage.showErrorDialog("Application doesn't exist","Invalid input");
                return;
            }
            if (password.isEmpty() || application.isEmpty()) {
                DialogMessage.showErrorDialog("All necessary information must be filled", "Invalid input");
                return;
            }
            int decision = DialogMessage.showDecisionDialog("Are you sure with the change",
                    "Are you sure about that");
            if (decision == 0) {
                ConnectDatabase.updateApplication(application, password);
                DialogMessage.showNotificationDialog(String.format("%s's password has been successfully changed", application),
                        "Password added");
            }
            else
                return;
        }
        catch (SQLException e) {
            DialogMessage.showErrorDialog("Couldn't connect to database :((", "Connection error");
        }
        catch (Exception e) {
            DialogMessage.showErrorDialog("Couldn't get Key", "Key Error");
        }
    }
    public void performGeneratePassword(JTextField textField) {
        textField.setText(PasswordGenerator.generatePassword(16));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PasswordManagerFrame());
    }
}
