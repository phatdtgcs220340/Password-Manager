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

    private JTextField searchApplicationTextField;
    private JTextArea searchInformationTextField;
    private JButton searchButton;
    private JButton generatePasswordButton;

    public PasswordManagerFrame() {
        setTitle("Password Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Add Tab
        JPanel addPanel = new JPanel();
        addPanel.setLayout(new GridLayout(5, 1, 10, 20));

        addApplicationTextField = new JTextField();
        addUsernameTextField = new JTextField();
        addPasswordTextField = new JTextField();
        addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension((int) (getWidth() * 0.8), addButton.getHeight()));
        generatePasswordButton = new JButton("Generate Password");
        generatePasswordButton.setPreferredSize(new Dimension((int) (getWidth() * 0.8), generatePasswordButton.getHeight()));

        addPanel.add(createPanel(new JLabel("Application: "), addApplicationTextField));
        addPanel.add(createPanel(new JLabel("Username:  "), addUsernameTextField));
        addPanel.add(createPanel(new JLabel("Password:  "), addPasswordTextField));
        addPanel.add(generatePasswordButton);
        addPanel.add(addButton);

        generatePasswordButton.addActionListener(e -> performGeneratePassword());

        addButton.addActionListener(e -> performAdd());
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
        // Add tabs to the tabbedPane
        tabbedPane.addTab("Add Application", addPanel);
        tabbedPane.addTab("Search Application", searchPanel);

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
            }
            searchInformationTextField.setText(result);
        } catch (SQLException e) {
            DialogMessage.showErrorDialog("Couldn't connect to database :((", "Connection error");
        }
    }

    public void performGeneratePassword() {
        addPasswordTextField.setText(PasswordGenerator.generatePassword(16));
    }
    public void performAdd() {
        try {
            String application = addApplicationTextField.getText();
            application = StringFormat.toCapitalize(application);
            String username = addUsernameTextField.getText();
            String password = addPasswordTextField.getText();
            if (username.isEmpty() || password.isEmpty() || application.isEmpty()) {
                DialogMessage.showErrorDialog("The information must be filled", "Invalid input");
                return;
            }
            ConnectDatabase.addApplication(application, username, password);
            DialogMessage.showNotificationDialog(String.format("%s is successfully added", application), "Application added");
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
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PasswordManagerFrame());
    }
}
