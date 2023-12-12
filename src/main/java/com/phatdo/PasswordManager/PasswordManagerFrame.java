package com.phatdo.PasswordManager;

import com.phatdo.ClipboardProcess.AutoCopy;
import com.phatdo.DataProcess.ApplicationProcess;
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
    private JComboBox searchApplicationTextField;
    private JTextArea searchInformationTextField;
    private JButton searchButton;

    private JTextField updateApplicationTextField;
    private JTextField updatePasswordTextField;
    private JButton updateButton;
    private JButton generatePasswordButton_update;

    private JTextField deleteApplicationTextField;
    private JButton deleteButton;

    public PasswordManagerFrame() {
        setTitle("Password Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Search Tab
        JPanel searchPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        try {
            searchApplicationTextField = new JComboBox<>(ApplicationProcess.applicationList().toArray(new String[0]));
        }
        catch (SQLException e) {
            DialogMessage.showErrorDialog("Couldn't connect to database :((", "Connection error");
        }
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
        updatePanel.add(new JLabel("Application:"), gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        updatePanel.add(updateApplicationTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        updatePanel.add(new JLabel("New Password:"), gbc);

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

        // Delete tab
        JPanel deletePanel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();

        deleteApplicationTextField = new JTextField();
        deleteButton = new JButton("Delete");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        deletePanel.add(new JLabel("Application: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        deletePanel.add(deleteApplicationTextField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        deletePanel.add(deleteButton, gbc);

        deleteButton.addActionListener(e -> performDelete());
        // Add tabs to the tabbedPane
        tabbedPane.addTab("Search", searchPanel);
        tabbedPane.addTab("Add", addPanel);
        tabbedPane.addTab("Update", updatePanel);
        tabbedPane.addTab("Delete", deletePanel);
        // Add tabbedPane to the frame
        add(tabbedPane);

        // Set up the frame
        setSize(400, 300);
        setVisible(true);
    }

    public void performSearch() {
        try {
            String applicationInput = StringFormat.toCapitalize((String) searchApplicationTextField.getSelectedItem());
            String result = ApplicationProcess.findApplication(applicationInput);
            if (Objects.equals(result, "")) {
                DialogMessage.showErrorDialog("Application doesn't exist", "Invalid input");
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
            String username = StringFormat.removeLeadingTrailingSpaces(addUsernameTextField.getText());
            String password = StringFormat.removeLeadingTrailingSpaces(addPasswordTextField.getText());
            if (username.isEmpty() || password.isEmpty() || application.isEmpty()) {
                DialogMessage.showErrorDialog("All necessary information must be filled", "Invalid input");
                return;
            }
            int decision = DialogMessage.showDecisionDialog("Are you sure with the change",
                    "Are you sure about that");
            if (decision == 0) {
                ApplicationProcess.addApplication(application, username, password);
                DialogMessage.showNotificationDialog(String.format("%s has been successfully added", application),
                        "Application added");
            } else
                return;
        } catch (PSQLException e) {
            DialogMessage.showErrorDialog("Application has already existed", "Unique constraint violated");
        } catch (SQLException e) {
            DialogMessage.showErrorDialog("Couldn't connect to database :((", "Connection error");
        } catch (Exception e) {
            DialogMessage.showErrorDialog("Couldn't get Key", "Key Error");
        }
    }

    public void performUpdate() {
        try {
            String application = updateApplicationTextField.getText();
            application = StringFormat.toCapitalize(application);
            String password = StringFormat.removeLeadingTrailingSpaces(updatePasswordTextField.getText());
            String result = ApplicationProcess.findApplication(application);

            if (Objects.equals(result, "")) {
                DialogMessage.showErrorDialog("Application doesn't exist", "Invalid input");
                return;
            }
            if (password.isEmpty() || application.isEmpty()) {
                DialogMessage.showErrorDialog("All necessary information must be filled", "Invalid input");
                return;
            }
            int decision = DialogMessage.showDecisionDialog("Are you sure with the change",
                    "Are you sure about that");
            if (decision == 0) {
                ApplicationProcess.updateApplication(application, password);
                DialogMessage.showNotificationDialog(
                        String.format("%s's password has been successfully changed", application),
                        "Password added");
            } else
                return;
        } catch (SQLException e) {
            DialogMessage.showErrorDialog("Couldn't connect to database :((", "Connection error");
        } catch (Exception e) {
            DialogMessage.showErrorDialog("Couldn't get Key", "Key Error");
        }
    }

    public void performDelete() {
        try {
            String application = deleteApplicationTextField.getText();
            application = StringFormat.toCapitalize(application);
            String result = ApplicationProcess.findApplication(application);
            if (application.isEmpty()) {
                DialogMessage.showErrorDialog("Application must be specified", "Invalid input");
                return;
            }
            if (Objects.equals(result, "")) {
                DialogMessage.showErrorDialog("Application doesn't exist", "Invalid input");
                return;
            }
            int decision = DialogMessage.showDecisionDialog("The application will be deleted",
                    "Are you sure about that");
            if (decision == 0) {
                ApplicationProcess.deleteApplication(application);
                DialogMessage.showNotificationDialog(
                        String.format("%s's has been deleted", application),
                        "Application deleted");
            }
            else
                return;

        }
        catch (SQLException e) {
            DialogMessage.showErrorDialog("Couldn't connect to database :((", "Connection error");
        }
    }
    public void performGeneratePassword(JTextField textField) {
        String generated_password = PasswordGenerator.generatePassword(16);
        AutoCopy.copyToClipboard(generated_password);
        textField.setText(generated_password);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PasswordManagerFrame());
    }
}
