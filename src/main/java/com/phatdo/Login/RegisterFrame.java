package com.phatdo.Login;

import com.phatdo.Cryptography.Cryptography;
import com.phatdo.DataProcess.OwnerProcess;
import com.phatdo.PasswordManager.DialogMessage;
import com.phatdo.StringFormatter.StringFormat;
import org.postgresql.util.PSQLException;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class RegisterFrame extends JFrame {
    private JTextField usernameTextField;
    private JPasswordField firstPasswordField;
    private JPasswordField secondPasswordField;
    private JButton registerButton;

    public RegisterFrame() {
        setTitle("Register Application");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        ImageIcon img = new ImageIcon("src/main/resources/img/frame_icon/frameicon.png");
        setIconImage(img.getImage());
        getContentPane().setBackground(Color.decode("#EEF5FF"));

        // Create components
        usernameTextField = new JTextField();
        firstPasswordField = new JPasswordField();
        secondPasswordField = new JPasswordField();
        registerButton = new JButton("Register");
        firstPasswordField.setEchoChar('*');
        secondPasswordField.setEchoChar('*');

        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
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
        gbc.gridwidth = 2;
        add(new JLabel("Password:"), gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(firstPasswordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(new JLabel("Confirm Password:"), gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(secondPasswordField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 0, 0);
        add(registerButton, gbc);

        registerButton.addActionListener(e -> performRegister());

    }
    private void performRegister() {
        String username = StringFormat.removeLeadingTrailingSpaces(usernameTextField.getText());
        char[] passwordChars = firstPasswordField.getPassword();
        String firstPassword = new String(passwordChars);
        passwordChars = secondPasswordField.getPassword();
        String secondPassword = new String(passwordChars);

        if (username.isEmpty() || firstPassword.isEmpty() || secondPassword.isEmpty()) {
            DialogMessage.showErrorDialog("You must fill all necessary information", "Input error");
            return;
        }
        if (!firstPassword.equals(secondPassword)) {
            DialogMessage.showErrorDialog("You must confirm the password", "Password mismatch");
            return;
        }
        try {
            int decision = DialogMessage.showDecisionDialog("You gonna add this user", "Are you sure?");
            if (decision == 0) {
                OwnerProcess.createNewOwner(username, Cryptography.encrypt(firstPassword));
                DialogMessage.showNotificationDialog("Register successfully",
                        "User added");
            }

        }
        catch (PSQLException e) {
            DialogMessage.showErrorDialog("Username existed","Duplicated constraint violated");
        }
        catch (SQLException e) {
            System.out.println("Couldn't connect to database :((");
        }
        catch (Exception e) {
            System.out.println("Key error");
        }
    }
}
