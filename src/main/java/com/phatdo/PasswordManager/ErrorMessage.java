package com.phatdo.PasswordManager;

import javax.swing.*;

public class ErrorMessage {
    public static void showErrorDialog(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

}
